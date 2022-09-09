package com.jenn.eventsinkorea.domain.buddy.repository;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> , BuddyDAO {
//    List<Buddy> findAllOrderByCreatedAtDesc();
    Page<Buddy> findAllByOrderByUpdatedAtDesc(Pageable pageable);


    Optional<Buddy> findByUser(User user);
}
