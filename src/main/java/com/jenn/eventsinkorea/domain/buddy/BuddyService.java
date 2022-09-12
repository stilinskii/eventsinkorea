package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.buddy.repository.BuddyRepository;
import com.jenn.eventsinkorea.domain.buddy.repository.BuddyRequestRepository;
import com.jenn.eventsinkorea.domain.file.S3Uploader;
import com.jenn.eventsinkorea.domain.user.model.User;
import com.jenn.eventsinkorea.web.buddy.form.BeABuddyForm;
import com.jenn.eventsinkorea.web.buddy.form.BuddyFilteringSortingOption;
import com.jenn.eventsinkorea.domain.email.EmailService;
import com.jenn.eventsinkorea.domain.email.MailInfo;
import com.jenn.eventsinkorea.domain.email.MailOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuddyService {
    private final BuddyRepository buddyRepository;

    private final BuddyRequestRepository buddyRequestRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    private final EmailService emailService;

    String UPLOAD_IMAGE_URL = "imageUrl";
    String UPLOAD_FILE_NAME = "fileName";

    @Transactional
    public void saveBuddy(BeABuddyForm form) {

        MultipartFile image = form.getImage();
        Map<String,String> S3Url;
        try {
            S3Url = s3Uploader.upload(image, "buddy");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Buddy buddy = Buddy.builder()
                .nativeLang(form.getNativeLang())
                .learningLang(form.getSecondLang())
                .location(form.getLocation())
                .intro(form.getIntro())
                .imgUrl(S3Url.get(UPLOAD_IMAGE_URL))
                .fileName(S3Url.get(UPLOAD_FILE_NAME))
                .user(userRepository.findByUsername(form.getUsername()))
                .likeCnt(0L)
                .build();

        buddyRepository.save(buddy);
    }

    @Transactional
    public void saveBuddy(Buddy buddy,MultipartFile image) {
        Buddy buddyToBeUpdated = buddyRepository.getOne(buddy.getId());
        log.info("buddy={}",buddyToBeUpdated);
        Map<String,String> S3Url;//1번째가
        try {
            if(!image.isEmpty()){
                s3Uploader.deleteFile(buddy.getFileName());
                S3Url = s3Uploader.upload(image, "buddy");
                buddyToBeUpdated.setImgUrl(S3Url.get(UPLOAD_IMAGE_URL));
                buddyToBeUpdated.setFileName(S3Url.get(UPLOAD_FILE_NAME));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        buddyToBeUpdated.setUpdatedAt(new Date());

        buddyRepository.save(buddyToBeUpdated);
    }


    public Slice<Buddy> getFilteredBuddies(BuddyFilteringSortingOption option , Pageable pageable){
        return buddyRepository.filteringBuddy(option,pageable);
    }

    public List<BuddyRequest> getRequestsByBuddyId(long id){
        return buddyRequestRepository.findByBuddy(buddyRepository.findById(id).get());
    }

    public BuddyRequest requestBuddy(String username, Long buddyId ){
        User user = userRepository.findByUsername(username);
        Integer duplicateCheck = buddyRequestRepository.duplicateRequest(user.getId(), buddyId);

        if(duplicateCheck>0){
            return null;
        }

        BuddyRequest buddyRequest = new BuddyRequest();
        buddyRequest.setStatus(0);//대기
        buddyRequest.setUser(user);
        Buddy buddy = buddyRepository.findById(buddyId).get();
        buddyRequest.setBuddy(buddy);

        MailInfo mailInfo = MailInfo.builder()
                .to(buddy.getUser().getName())
                .email(buddy.getUser().getEmail())
                .by(user.getName())
                .mailOption(MailOption.REQUESTED).build();
        emailService.send(mailInfo);


        return buddyRequestRepository.save(buddyRequest);
    }




    public void addLikeCnt(Long buddyId, String username) {
        //buddy like 업데이트
        Buddy buddyToBeUpdated = buddyRepository.getById(buddyId);
        buddyToBeUpdated.setLikeCnt(buddyToBeUpdated.getLikeCnt()+1);
        //like_table에 추가
        User user = userRepository.findByUsername(username);
        buddyToBeUpdated.getLikedUsers().add(user);

        buddyRepository.save(buddyToBeUpdated);
    }

    public void subtractLikeCnt(Long buddyId, String username) {
        //buddy like 업데이트
        Buddy buddyToBeUpdated = buddyRepository.getById(buddyId);
        buddyToBeUpdated.setLikeCnt(buddyToBeUpdated.getLikeCnt()-1);
        //like_table에 추가
        User user = userRepository.findByUsername(username);
        buddyToBeUpdated.getLikedUsers().remove(user);

        buddyRepository.save(buddyToBeUpdated);
    }

    public Buddy findBuddyByUsername(String name) {
        User user = userRepository.findByUsername(name);
        return buddyRepository.findByUser(user)
                .orElse(null);
    }

    public void deleteBuddy(Long id) {
        Buddy buddy = buddyRepository.getOne(id);
        s3Uploader.deleteFile(buddy.getFileName());
        buddyRepository.delete(buddy);
    }
}
