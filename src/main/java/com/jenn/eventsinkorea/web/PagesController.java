package com.jenn.eventsinkorea.web;

import com.jenn.eventsinkorea.domain.admin.repository.PageRepository;
import com.jenn.eventsinkorea.domain.admin.model.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PagesController {

        private final PageRepository pageRepository;

        @GetMapping("/{slug}")
        public String page(@PathVariable String slug, Model model){
            Page page = pageRepository.findBySlug(slug);
            if(page==null){
                return "redirect:/";
            }
            model.addAttribute("page",page);
            return "page";
        }

    // slug받고 해당 slug 홈페이지로 redirect.
    //page자체가 존재하지 않으면(findbyslug) 홈페이지로 리다이렉트.
}
