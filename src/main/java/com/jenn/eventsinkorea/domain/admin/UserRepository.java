package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserIdAndIdNot(String userId, Integer id);
    User findByUserId(String userId);
    //List<User> findByCreatedAtAfter(Date today);

//    List<User> findByUserIdContaining(String userId);
//
//    List<User> findByEmailContaining(String email);
//    List<User> findByUsernameContaining(String username);
//
//    List<User> findByUserIdContainingOrEmailContainingOrUsernameContaining(String keyword);
}
