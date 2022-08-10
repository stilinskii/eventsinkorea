package com.jenn.eventsinkorea.web.buddy;

import com.jenn.eventsinkorea.web.buddy.form.BeABuddyForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BeABuddyFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(BeABuddyForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BeABuddyForm form = (BeABuddyForm) target;
        if(form.getNativeLang().equals(form.getSecondLang())){
            errors.rejectValue("secondLang","key","Learning language can't be the same as Native language. Choose another.");
        }
    }
}
