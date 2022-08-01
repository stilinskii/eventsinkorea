package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.admin.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByUserIdAndIdNot() {
        //given
        User user = User.builder()
                .id(1)
                .userId("hhgggg")
                .pwd("123212312")
                .username("herong")
                .email("lhy98410@naver.com")
                .nationality("Korea")
                .build();
        underTest.save(user);
        //when
        User byUserIdAndIdNot = underTest.findByUserIdAndIdNot(user.getUserId(), user.getId() - 1);

        //then
        assertThat(byUserIdAndIdNot).isNotNull();
    }
}