package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuddyReviewService {

    private final BuddyReviewRepository buddyReviewRepository;
    private final BuddyRequestRepository buddyRequestRepository;

    @Transactional
    public void saveReview(BuddyReview review){
        BuddyRequest buddyRequest = buddyRequestRepository.findByBuddyAndUser(review.getBuddy(), review.getUser()).get();
        buddyRequest.setReview(1);//해당 신청건의 리뷰작성 완료됨 표시.
        buddyReviewRepository.save(review);

    }
}
