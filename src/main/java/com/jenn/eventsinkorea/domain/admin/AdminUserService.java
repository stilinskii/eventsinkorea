package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.admin.model.User;
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
    private final UserRepository userRepo;


    public List<User> findAll(){
        return userRepo.findAll();
    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

    public void editUserInfo(UserEditForm form, Long id) {

        User userToBeEdited = userRepo.getById(id);
        userToBeEdited.setUserId(form.getUserId());
        userToBeEdited.setUsername(form.getUsername());
        userToBeEdited.setEmail(form.getEmail());
        userToBeEdited.setNationality(form.getNationality());

        userRepo.save(userToBeEdited);


    }

    public List<User> findUsersBySearch(UserSearchForm form){
        return userRepo.findUsersBySearch(form);
    }
}
