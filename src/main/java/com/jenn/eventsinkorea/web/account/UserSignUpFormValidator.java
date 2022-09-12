package com.jenn.eventsinkorea.web.account;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.web.account.form.UserSignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserSignUpFormValidator implements Validator {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserSignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserSignUpForm form = (UserSignUpForm) target;
        if(userRepository.findByUsername(form.getUsername())!=null){
            errors.rejectValue("username","usernameExists");

        }
    }
}
