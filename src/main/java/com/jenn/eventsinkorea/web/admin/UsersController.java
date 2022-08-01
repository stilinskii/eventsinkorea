package com.jenn.eventsinkorea.web.admin;

import com.jenn.eventsinkorea.domain.admin.model.User;
import com.jenn.eventsinkorea.domain.admin.UserRepository;
import com.jenn.eventsinkorea.domain.admin.UserService;
import com.jenn.eventsinkorea.web.admin.form.UserEditForm;
import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UserRepository userRepo;
    private final UserService userService;

    private final UserEditFormValidator userEditFormValidator;

    @GetMapping
    public String users(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin/users/index";
    }

    @GetMapping("edit")
    public String edit(Integer id,Model model){
        User user = userRepo.getById(id);
        model.addAttribute("user",user);
        return "admin/users/edit";
    }


    //아이디를 꼭 넘겨야하나? TODO
    @PostMapping("edit")
    public String editSubmit(@Validated @ModelAttribute("user") UserEditForm form, BindingResult bindingResult, Integer id, RedirectAttributes redirectAttributes){
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
    public String delete(Integer id){
        userService.deleteUser(id);

        return "redirect:/admin/users";
    }

//    @PostMapping("/search")
//    public String search(UserSearchForm form,Model model){
//        log.info("form.getOption={}",form.getOption());
//        List<User> userBySearch = userService.findBySearch(form);
//        model.addAttribute("users",userBySearch);
//        return "admin/users/index";
//    }


}
