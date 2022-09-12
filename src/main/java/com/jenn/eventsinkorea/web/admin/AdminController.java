package com.jenn.eventsinkorea.web.admin;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
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



    private final UserRepository userRepository;

    @GetMapping
    public String index(Model model){
        Long todayJoinedUsers = userRepository.todayJoinedUsersCnt();
        TodayVisit todayVisit = TodayVisit.getInstance();
        model.addAttribute("visitorCnt",todayVisit.getTodayVisitCnt());
        model.addAttribute("usersCnt",todayJoinedUsers);
        return "admin/index";
    }



}
