package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private AdminUserService underTest;

    @BeforeEach
    void setUp() {
        //before each test get fresh Repository instance
        underTest = new AdminUserService(userRepository);
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
        underTest.deleteUser(anyLong());
        //then
        verify(userRepository).deleteById(anyLong());
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