package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.admin.model.User;
import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserDAOImplTest {

    @Autowired
    private JPAQueryFactory query;

    @Autowired
    private UserRepository userRepository;

    @Test
    void setUp() {
        userRepository.save(new User("dfdfd","ldskfjaldsk!dfd","haeggd","lhy98410@nate.com","Korea"));
        userRepository.save(new User("dfdfsdfd","jklky765654!dfd","hhhhh","lhy98h410@nate.com","China"));
        userRepository.save(new User("hgkhjg","dffdfsdg!dfd","jjjjj","lhy9841hh0@nate.com","Japan"));
    }


}