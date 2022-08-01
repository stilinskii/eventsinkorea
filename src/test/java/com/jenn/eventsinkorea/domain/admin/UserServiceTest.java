package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService underTest;

    @BeforeEach
    void setUp() {
        //before each test get fresh Repository instance
        underTest = new UserService(userRepository);
    }

    @Test
    void findAll() {
        //when
        underTest.findAll();
        //then
        verify(userRepository).findAll();
    }

    @Test
    void deleteUser() {
        //when
        underTest.deleteUser(anyInt());
        //then
        verify(userRepository).deleteById(anyInt());
    }

    @Test
    void editUserInfo() {
    }

//    @Test
//    void findBySearch() {
//        UserSearchForm form = new UserSearchForm();
//        form.setKeyword("0");
//        form.setOption("UserId");
//        underTest.findBySearch(form);
//    }
}