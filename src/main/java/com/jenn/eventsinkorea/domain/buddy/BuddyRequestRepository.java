package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.buddy.model.UserBuddyId;
import com.jenn.eventsinkorea.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuddyRequestRepository extends JpaRepository<BuddyRequest, UserBuddyId> {
    List<BuddyRequest> findByBuddy(Buddy buddy);
    List<BuddyRequest> findByUser(User user);

    @Query(value =
            "SELECT COUNT(*) " +
            "FROM buddy_request " +
            "WHERE user_id = :userId " +
            "AND buddy_id = :buddyId"
            ,nativeQuery = true
    )
    Integer duplicateRequest(@Param("userId") Long userId,@Param("buddyId") Long buddyId);

//    @Query(value = "SELECT * FROM buddy_request WHERE)", nativeQuery = true)
//    BuddyRequest findBuddyRequestByUsernameAndBuddyId(@Param("request") BuddyRequest buddyRequest);
}
