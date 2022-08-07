package com.jenn.eventsinkorea.web.admin;


import com.jenn.eventsinkorea.domain.admin.repository.CategoryRepository;
import com.jenn.eventsinkorea.domain.admin.repository.PageRepository;
import com.jenn.eventsinkorea.domain.admin.model.Category;
import com.jenn.eventsinkorea.web.admin.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoriesController {

    private final CategoryRepository categoryRepository;
    private final PageRepository pageRepository;
    private final CategoryValidator categoryValidator;


    @GetMapping
    public String index(Model model){
        List<Category> categories = categoryRepository.findAllByOrderBySortingAsc();
        model.addAttribute("categories",categories);
        return "admin/categories/index";
    }

    //ajax
    @PostMapping("/dataSend")
    public String dataSend(Model model, Long pageId){
        List<Category> categories = categoryRepository.findCategoriesByPageId(pageId);
        model.addAttribute("categories",categories);
        return "admin/categories/index :: #categories";
    }

    @GetMapping("/add")
    public String add(Category category){
        return "admin/categories/add";
    }

    @PostMapping("/add")
    public String forAddSubmit(@Valid Category category, BindingResult bindingResult, Long pageId, RedirectAttributes redirectAttributes){

        categoryValidator.validate(category,bindingResult);
        if(bindingResult.hasErrors()){
            //model.addAttribute("errors",bindingResult); 생략가능
            log.info("bindingResult={}",bindingResult);
            return "admin/categories/add";
        }

        //성공로직
        String slug = category.getName().toLowerCase().replace(" ","-");
        category.setPage(pageRepository.getById(pageId));
        category.setSlug(slug);
        category.setSorting(100);
        categoryRepository.save(category);

        redirectAttributes.addFlashAttribute("message","Category added");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        return "redirect:/admin/categories/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "admin/categories/edit";
    }

    @PostMapping("/edit")
    public String forEditSubmit(@Valid Category category, BindingResult bindingResult,Model model, RedirectAttributes redirectAttributes){

        categoryValidator.validate(category,bindingResult);
        if(bindingResult.hasErrors()){
            //수정할때 카테고리 이름이 지워져도 페이지 제목에 원래 수정하려했던 페이지 이름 표시
            Category categoryCurrent = categoryRepository.findById(category.getId()).get();
            model.addAttribute("categoryName",categoryCurrent.getName());
            return "admin/categories/edit";
        }

       //성공로직
        String slug = category.getName().toLowerCase().replace(" ","-");
        category.setSlug(slug);
        categoryRepository.save(category);
        redirectAttributes.addFlashAttribute("message","Category edited");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        return "redirect:/admin/categories/edit/"+category.getId();
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        categoryRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message","Category deleted");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");
        return "redirect:/admin/categories";
    }

    @ResponseBody
    @PostMapping("/reorder")
    public void reorder(@RequestParam("id[]") int[] id){
        int count = 1;
        Category category;
        for (int categoryId : id) {
            category = categoryRepository.findById((long) categoryId).get();
            category.setSorting(count);
            categoryRepository.save(category);
            count++;
        }
        //return "ok";
    }

}
