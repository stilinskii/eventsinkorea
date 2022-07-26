package com.jenn.eventsinkorea.web.admin;

import com.jenn.eventsinkorea.domain.admin.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepo;

    @GetMapping
    public String index(){
        return "admin/index";
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "admin/users";
    }



}
