package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyReview;
import com.jenn.eventsinkorea.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuddyReviewRepository extends JpaRepository<BuddyReview,Long> {

    List<BuddyReview> findByUser(User user);

    List<BuddyReview> findByBuddy(Buddy buddy);


//    @Query(
//        value =
//                "SELECT rating " +
//                        "FROM buddy_review " +
//                        "WHERE buddy_id = :buddyId "
//                ,nativeQuery = true
//    )
//    List<Double> findRatingByBuddyId(@Param("buddyId") Long buddyId);

    @Query(value = "SELECT count(*) " +
            "FROM buddy_review " +
            "WHERE buddy_id = :buddyId "
                    ,nativeQuery = true)
    Integer countReviewByBuddyId(@Param("buddyId") Long buddyId);

}
