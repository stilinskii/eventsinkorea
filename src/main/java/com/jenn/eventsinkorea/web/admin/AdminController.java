package com.jenn.eventsinkorea.web.admin;

import com.jenn.eventsinkorea.domain.admin.AdminService;
import com.jenn.eventsinkorea.domain.admin.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {


    private final AdminService adminService;

    @GetMapping
    public String index(Model model){
        Integer todayJoinedUsersCnt = adminService.getTodayJoinedUsersCnt();
        log.info("cnt={}",todayJoinedUsersCnt);
        model.addAttribute("usersCnt",todayJoinedUsersCnt);
        return "admin/index";
    }






}
