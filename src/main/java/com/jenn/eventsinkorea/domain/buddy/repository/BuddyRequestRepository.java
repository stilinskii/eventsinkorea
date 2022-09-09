package com.jenn.eventsinkorea.domain.buddy.repository;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.buddy.model.UserBuddyId;
import com.jenn.eventsinkorea.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuddyRequestRepository extends JpaRepository<BuddyRequest, UserBuddyId> {
    List<BuddyRequest> findByBuddy(Buddy buddy);
    List<BuddyRequest> findByUserOrderByRequestDateDesc(User user);

    List<BuddyRequest> findByUserAndStatusAndReview(User user, Integer status, Integer reviewStatus);

    Optional<BuddyRequest> findByBuddyAndUser(Buddy buddy, User user);

    //waiting list 보기위해
    List<BuddyRequest> findByBuddyAndStatusOrderByRequestDateDesc(Buddy buddy, Integer status);
    //waiting 제외한 다른것들
    List<BuddyRequest> findByBuddyAndStatusNotOrderByRequestDateDesc(Buddy buddy, Integer status);

    @Query(value =
            "SELECT COUNT(*) " +
            "FROM buddy_request " +
            "WHERE user_id = :userId " +
            "AND buddy_id = :buddyId"
            ,nativeQuery = true
    )
    Integer duplicateRequest(@Param("userId") Long userId,@Param("buddyId") Long buddyId);



    @Query(value =
            "DELETE FROM buddy_request WHERE user_id = :userId AND buddy_id = :buddyId"
            ,nativeQuery = true
    )
    void deleteByUserAndBuddy(@Param("userId") Long userId,@Param("buddyId") Long buddyId);



}
