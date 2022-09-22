package com.jenn.eventsinkorea.domain.admin.repository;

import com.jenn.eventsinkorea.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , UserDAO {


    User findByUsernameAndIdNot(String userId, Long id);
    User findByUsername(String username);

}
