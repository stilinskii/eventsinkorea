package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> , BuddyDAO{
//    List<Buddy> findAllOrderByCreatedAtDesc();
    Page<Buddy> findAllByOrderByUpdatedAtDesc(Pageable pageable);
}
