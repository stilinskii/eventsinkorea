package com.jenn.eventsinkorea.domain.admin.repository;


import com.jenn.eventsinkorea.domain.admin.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    Category findByNameAndIdNot(String name,Long id);

    List<Category> findAllByOrderBySortingAsc();
   // @Query(value="select * from likes where imageId in (select id from image where userId = ?1) order by id desc limit 5;", nativeQuery = true)
    @Query(value = "select * from categories where page_Id = ?1 order by sorting asc", nativeQuery = true)
    List<Category> findCategoriesByPageId(Long pageId);
    Category findBySlug(String slug);
}
