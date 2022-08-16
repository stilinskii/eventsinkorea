package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.file.S3Uploader;
import com.jenn.eventsinkorea.web.buddy.form.BeABuddyForm;
import com.jenn.eventsinkorea.web.buddy.form.BuddyFilteringOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    public List<Buddy> getFilteredbuddies(BuddyFilteringOption option){
        return buddyRepository.filteringBuddy(option);
    }

    public List<BuddyRequest> getRequestsByBuddyId(long id){
        return buddyRequestRepository.findByBuddy(buddyRepository.findById(id).get());
    }

    public BuddyRequest saveRequest(String username,Long buddyId ){
        BuddyRequest buddyRequest = new BuddyRequest();
        buddyRequest.setStatus(0);//대기
        buddyRequest.setUser(userRepository.findByUsername(username));
        buddyRequest.setBuddy(buddyRepository.findById(buddyId).get());
        return buddyRequestRepository.save(buddyRequest);
    }
}
