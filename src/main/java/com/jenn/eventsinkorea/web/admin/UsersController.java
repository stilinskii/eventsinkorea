package com.jenn.eventsinkorea.web.admin;

import com.jenn.eventsinkorea.domain.admin.User;
import com.jenn.eventsinkorea.domain.admin.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UserRepository userRepo;

    @GetMapping
    public String users(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "admin/users/index";
    }

    @GetMapping("edit")
    public String edit(Integer id,Model model){
        User user = userRepo.getById(id);
        model.addAttribute("user",user);
        return "admin/users/edit";
    }


    @PostMapping("edit")
    public String editSubmit(@Validated @ModelAttribute User user, BindingResult bindingResult, Integer id){
        User userToBeEdited = userRepo.getById(id);
        userToBeEdited.setUser_id(user.getUser_id());
        userToBeEdited.setUsername(user.getUsername());
        userToBeEdited.setEmail(user.getEmail());
        userToBeEdited.setNationality(user.getNationality());

        userRepo.save(userToBeEdited);
        return "redirect:/admin/users/edit";
    }

}
