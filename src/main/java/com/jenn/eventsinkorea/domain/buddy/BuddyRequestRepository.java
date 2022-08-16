package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.buddy.model.UserBuddyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuddyRequestRepository extends JpaRepository<BuddyRequest, UserBuddyId> {
    List<BuddyRequest> findByBuddy(Buddy buddy);

//    @Query(value = "INSERT INTO buddy_request VALUES (:#{#request.user.id},:#{#request.buddy.id},:#{#request.status})", nativeQuery = true)
//    BuddyRequest saveBuddyRequest(@Param("request") BuddyRequest buddyRequest);
}
