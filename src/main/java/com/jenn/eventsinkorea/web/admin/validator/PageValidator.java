package com.jenn.eventsinkorea.web.admin.validator;

import com.jenn.eventsinkorea.domain.admin.PageRepository;
import com.jenn.eventsinkorea.domain.admin.model.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class PageValidator implements Validator {

    private final PageRepository pageRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Page.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Page page = (Page) target;
        log.info("page={}",page);
        Page pageExists = page.getId()==null ? pageRepository.findBySlug(page.getSlug())
                : pageRepository.findBySlugAndIdNot(page.getSlug(),page.getId());
        if(pageExists != null){
            errors.rejectValue("slug","key","Slug exists, choose another");
        }
    }

}
