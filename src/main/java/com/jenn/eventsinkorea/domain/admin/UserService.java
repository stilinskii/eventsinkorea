package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.admin.model.User;
import com.jenn.eventsinkorea.web.admin.SearchOption;
import com.jenn.eventsinkorea.web.admin.form.UserEditForm;
import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;


    public List<User> findAll(){
        return userRepo.findAll();
    }

    public void deleteUser(Integer id){
        userRepo.deleteById(id);
    }

    public void editUserInfo(UserEditForm form, Integer id) {

        User userToBeEdited = userRepo.getById(id);
        userToBeEdited.setUserId(form.getUserId());
        userToBeEdited.setUsername(form.getUsername());
        userToBeEdited.setEmail(form.getEmail());
        userToBeEdited.setNationality(form.getNationality());

        userRepo.save(userToBeEdited);


    }

//    public List<User> findBySearch(UserSearchForm form) {
//
////            log.info("SearchOption.ID={}",SearchOption.);
//        if(form.getOption().equals("Id")){
//            List<User> byUserIdContaining = userRepo.findByUserIdContaining(form.getKeyword());
//            log.info("form.getKeyword()={}",form.getKeyword());
//            return byUserIdContaining;
//        }else if(form.getOption().equals("Username")){
//            List<User> byUsernameContaining = userRepo.findByUsernameContaining(form.getKeyword());
//            return byUsernameContaining;
//        }else if(form.getOption().equals("Email")){
//            userRepo.findByEmailContaining(form.getKeyword());
//        }
//
//
//        return userRepo.findByUserIdContainingOrEmailContainingOrUsernameContaining(form.getKeyword());
//    }
}
