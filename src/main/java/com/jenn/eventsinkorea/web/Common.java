package com.jenn.eventsinkorea.web;


import com.jenn.eventsinkorea.domain.admin.repository.CategoryRepository;
import com.jenn.eventsinkorea.domain.admin.repository.PageRepository;
import com.jenn.eventsinkorea.domain.admin.model.Page;
import com.jenn.eventsinkorea.web.admin.TodayVisit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

//intercepter 적용. 다른 메서드전에 이거 호출
@Slf4j
@ControllerAdvice
public class Common {
    @Autowired
    private PageRepository pageRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @ModelAttribute
    public void shareData(Model model, HttpSession session){
        List<Page> pages = pageRepo.findAllByOrderBySortingAsc();

        model.addAttribute("cpages",pages);


    }





}
