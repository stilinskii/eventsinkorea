package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> , BuddyDAO{
//    List<Buddy> findAllOrderByCreatedAtDesc();
}
