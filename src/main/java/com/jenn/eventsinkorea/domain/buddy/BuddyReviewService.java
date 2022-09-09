package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyReview;
import com.jenn.eventsinkorea.domain.buddy.repository.BuddyRepository;
import com.jenn.eventsinkorea.domain.buddy.repository.BuddyRequestRepository;
import com.jenn.eventsinkorea.domain.buddy.repository.BuddyReviewRepository;
import com.jenn.eventsinkorea.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuddyReviewService {

    private final BuddyRepository buddyRepository;
    private final BuddyReviewRepository buddyReviewRepository;
    private final BuddyRequestRepository buddyRequestRepository;

    @Transactional
    public void saveReview(BuddyReview review){
        Buddy buddy = review.getBuddy();
        User user = review.getUser();

        //해당 신청건의 리뷰작성 완료됨 표시.
        BuddyRequest buddyRequest = buddyRequestRepository.findByBuddyAndUser(buddy, user).get();
        buddyRequest.setReview(1);

        //버디 rating 평균치수 구하고 업데이트하기
        setRatingsAverageToBuddy(review.getRating(), buddy);

        //성공로직
        buddyReviewRepository.save(review);
    }

    private void setRatingsAverageToBuddy(Double newlyAddedRating, Buddy buddy) {
        //필요한 값 : 리뷰개수, 별점 합.
        List<BuddyReview> buddyReviews = buddy.getBuddyReviews();
        Integer count = buddyReviews.size() + 1; // 리뷰개수
        List<Double> ratings = buddyReviews.stream().map(BuddyReview::getRating).collect(Collectors.toList());
        double sum = ratings.stream().mapToDouble(Double::doubleValue).sum() + newlyAddedRating; //합계
        double average = sum / count;//평균


        buddy.setRating(Math.round(average * 10) / 10.0);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        BuddyReview review = buddyReviewRepository.findById(reviewId).get();
        //리퀘스트 리뷰 안했음으로 표시
        buddyRequestRepository.findByBuddyAndUser(review.getBuddy(),review.getUser()).get().setReview(null);
        buddyReviewRepository.deleteById(reviewId);

    }
}
