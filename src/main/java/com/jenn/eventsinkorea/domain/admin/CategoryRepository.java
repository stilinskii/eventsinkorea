package com.jenn.eventsinkorea.domain.admin;


import com.jenn.eventsinkorea.domain.admin.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    Category findByNameAndIdNot(String name,Long id);

    List<Category> findAllByOrderBySortingAsc();
    List<Category> findByPageIdOrderBySortingAsc(Long pageId);
    Category findBySlug(String slug);
}
