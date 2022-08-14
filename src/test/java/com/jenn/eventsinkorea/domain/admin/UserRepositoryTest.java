package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.user.User;
import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

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
                .id(1L)
                .username("hhgggg")
                .pwd("123212312")
                .name("herong")
                .email("lhy98410@naver.com")
                .nationality("Korea")
                .build();
        underTest.save(user);
        //when
        User byUserIdAndIdNot = underTest.findByUsernameAndIdNot(user.getUsername(), user.getId() - 1);

        //then
        assertThat(byUserIdAndIdNot).isNotNull();
    }
}