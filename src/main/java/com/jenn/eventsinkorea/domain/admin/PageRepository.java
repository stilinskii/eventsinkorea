package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.admin.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page,Long> {
    List<Page> findAllByOrderBySortingAsc();
    Page findBySlug(String slug);

    Page findBySlugAndIdNot(String slug, Long id);
}
