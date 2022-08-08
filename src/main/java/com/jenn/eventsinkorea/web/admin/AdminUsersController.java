package com.jenn.eventsinkorea.web.admin;

import com.jenn.eventsinkorea.domain.admin.model.User;
import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.admin.AdminUserService;
import com.jenn.eventsinkorea.web.admin.form.UserEditForm;
import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;
import com.jenn.eventsinkorea.web.admin.validator.UserEditFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
public class AdminUsersController {

    private final UserRepository userRepo;
    private final AdminUserService userService;

    private final UserEditFormValidator userEditFormValidator;

//    @InitBinder
//    protected void initBinder(WebDataBinder binder){
//        binder.registerCustomEditor(Date.class);
//    }

    @GetMapping
    public String users(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin/users/index";
    }

    @GetMapping("edit")
    public String edit(Long id,Model model){
        User user = userRepo.getById(id);
        model.addAttribute("user",user);
        return "admin/users/edit";
    }


    //아이디를 꼭 넘겨야하나? TODO
    @PostMapping("edit")
    public String editSubmit(@Validated @ModelAttribute("user") UserEditForm form, BindingResult bindingResult, Long id, RedirectAttributes redirectAttributes){
        userEditFormValidator.validate(form,bindingResult);
        if(bindingResult.hasErrors()){
            log.info("bindingResult={}",bindingResult);
            return "admin/users/edit";
        }

        //성공로직
        userService.editUserInfo(form, id);
        redirectAttributes.addFlashAttribute("edit","Edit Success");

        return "redirect:/admin/users/edit?id="+id;
    }

    @GetMapping("/delete")
    public String delete(Long id){
        userService.deleteUser(id);

        return "redirect:/admin/users";
    }

    @PostMapping("/search")
    public String search(UserSearchForm form, Model model){
        log.info("form.getOption={}",form.getOption());
        List<User> userBySearch = userService.findUsersBySearch(form);
        model.addAttribute("users",userBySearch);
        return "admin/users/index";
    }


}
