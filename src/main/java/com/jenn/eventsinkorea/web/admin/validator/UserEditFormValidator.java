package com.jenn.eventsinkorea.web.admin.validator;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.user.model.User;
import com.jenn.eventsinkorea.web.admin.form.UserEditForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserEditFormValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEditForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEditForm form = (UserEditForm) target;
        User userIdExists = userRepository.findByUsernameAndIdNot(form.getUsername(), form.getId());
        if(userIdExists!=null){
            errors.rejectValue("userId","usernameExists"); //TODO
        }


    }
}
