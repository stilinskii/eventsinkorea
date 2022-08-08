package com.jenn.eventsinkorea.domain.admin.repository;

import com.jenn.eventsinkorea.domain.admin.UserDAO;
import com.jenn.eventsinkorea.domain.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , UserDAO {

    User findByUserIdAndIdNot(String userId, Long id);
    User findByUserId(String userId);

}
