package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.file.S3Uploader;
import com.jenn.eventsinkorea.domain.user.User;
import com.jenn.eventsinkorea.web.buddy.form.BeABuddyForm;
import com.jenn.eventsinkorea.web.buddy.form.BuddyFilteringSortingOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuddyService {
    private final BuddyRepository buddyRepository;

    private final BuddyRequestRepository buddyRequestRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    public void saveBuddy(BeABuddyForm form) {

        MultipartFile image = form.getImage();
        String S3Url;
        try {
            S3Url = s3Uploader.upload(image, "buddy");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //user는 임시
        Buddy buddy = Buddy.builder()
                .nativeLang(form.getNativeLang())
                .learningLang(form.getSecondLang())
                .location(form.getLocation())
                .intro(form.getIntro())
                .imgUrl(S3Url)
                .user(userRepository.findById(15L).get())
                .build();

        buddyRepository.save(buddy);

    }

    public Slice<Buddy> getFilteredbuddies(BuddyFilteringSortingOption option , Pageable pageable){
        return buddyRepository.filteringBuddy(option,pageable);
    }

    public List<BuddyRequest> getRequestsByBuddyId(long id){
        return buddyRequestRepository.findByBuddy(buddyRepository.findById(id).get());
    }

    public BuddyRequest saveRequest(String username,Long buddyId ){
        User user = userRepository.findByUsername(username);
        Integer duplicateCheck = buddyRequestRepository.duplicateRequest(user.getId(), buddyId);

        if(duplicateCheck>0){
            return null;
        }

        log.info("chk={}",userRepository.findByUsername(username));

        BuddyRequest buddyRequest = new BuddyRequest();
        buddyRequest.setStatus(0);//대기
        buddyRequest.setUser(user);
        buddyRequest.setBuddy(buddyRepository.findById(buddyId).get());
        return buddyRequestRepository.save(buddyRequest);
    }

    public List<BuddyRequest> getRequestByUsername(String name){
        return buddyRequestRepository.findByUser(userRepository.findByUsername(name));
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
}
