package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.BuddyReview;
import com.jenn.eventsinkorea.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuddyReviewRepository extends JpaRepository<BuddyReview,Long> {

    List<BuddyReview> findByUser(User user);
}
