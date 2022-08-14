package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.user.User;
import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.web.admin.form.UserEditForm;
import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository userRepository;


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void editUserInfo(UserEditForm form, Long id) {

        User userToBeEdited = userRepository.getById(id);
        userToBeEdited.setUsername(form.getUserId());
        userToBeEdited.setName(form.getUsername());
        userToBeEdited.setEmail(form.getEmail());
        userToBeEdited.setNationality(form.getNationality());

        userRepository.save(userToBeEdited);


    }

    public List<User> findUsersBySearch(UserSearchForm form){
        return userRepository.findUsersBySearch(form);
    }
}
