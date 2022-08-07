package com.jenn.eventsinkorea.web.admin.validator;

import com.jenn.eventsinkorea.domain.admin.repository.CategoryRepository;
import com.jenn.eventsinkorea.domain.admin.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryValidator implements Validator {

    private final CategoryRepository categoryRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;
        //add일때와 edit일때.
        Category categoryExists = category.getId()==null ? categoryRepo.findByName(category.getName())
                :categoryRepo.findByNameAndIdNot(category.getName(), category.getId());

        if(categoryExists!=null){
            errors.rejectValue("name","key","Category exists, choose another");
        }
    }
}
