package com.jenn.eventsinkorea.web.admin;

import com.jenn.eventsinkorea.domain.admin.AdminPagesService;
import com.jenn.eventsinkorea.domain.admin.PageRepository;
import com.jenn.eventsinkorea.domain.admin.model.Page;
import com.jenn.eventsinkorea.web.admin.validator.PageValidator;
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
@RequestMapping("/admin/pages")
@RequiredArgsConstructor
public class AdminPagesController {
    private final PageRepository pageRepository;
    private final AdminPagesService service;

    private final PageValidator pageValidator;

    @GetMapping
    public String index(Model model){
        List<Page> pages = service.getPages();
        model.addAttribute("pages",pages);
        return "admin/pages/index";
    }

    @GetMapping("/add")
    public String addPageForm(@ModelAttribute Page page){
        return "admin/pages/add";
    }

    @PostMapping("/add")
    public String forAddSubmit(@Valid Page page, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        //slug 입력 안했으면 자동으로 넣어주고, 입력했으면 다듬기.
        String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ","-"):page.getSlug().toLowerCase().replace(" ","-");
        page.setSlug(slug);

        //그리고 검사받기
        pageValidator.validate(page,bindingResult);
        if(bindingResult.hasErrors()){
            return "admin/pages/add";
        }

        //성공로직
        page.setSorting(100);
        pageRepository.save(page);

        redirectAttributes.addFlashAttribute("message","Page added");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        return "redirect:/admin/pages/add";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model){
        model.addAttribute("page",pageRepository.findById(id).get());

        return "admin/pages/edit";
    }


    @PostMapping("/edit")
    public String forEditSubmit(@Valid Page page, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        //slug 입력 안했으면 자동으로 넣어주고, 입력했으면 다듬기.
        String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ","-"):page.getSlug().toLowerCase().replace(" ","-");
        page.setSlug(slug);

        //그리고 검사받기
        pageValidator.validate(page,bindingResult);
        if(bindingResult.hasErrors()){
            //오류로 저장되지 못했을때 사용자가 수정한 title이 아닌 원래 title 유지를 위해
            Page pageCurrent = pageRepository.getOne(page.getId());
            model.addAttribute("pageTitle",pageCurrent.getTitle());
            return "admin/pages/edit";
        }

        //성공로직
        pageRepository.save(page);
        redirectAttributes.addFlashAttribute("message","Page edited");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        return "redirect:/admin/pages/edit/"+page.getId();
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes){
        pageRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message","Page deleted");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        return "redirect:/admin/pages";
    }


    //드래그 정렬 구현
    @ResponseBody
    @PostMapping("/reorder")
    public String reorder(@RequestParam("id[]") int[] id){
        int count = 1;
        Page page;

        for (int pageId : id) {
            page = pageRepository.getOne(pageId);
            page.setSorting(count);
            pageRepository.save(page);
            count++;
        }

        return "ok";
    }

}
