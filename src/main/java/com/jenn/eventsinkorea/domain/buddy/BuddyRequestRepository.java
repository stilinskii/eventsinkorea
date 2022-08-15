package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.buddy.model.UserBuddyId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuddyRequestRepository extends JpaRepository<BuddyRequest, UserBuddyId> {
    List<BuddyRequest> findByBuddy(Buddy buddy);
}
