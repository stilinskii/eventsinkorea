package com.jenn.eventsinkorea.web.admin;

import com.jenn.eventsinkorea.domain.admin.User;
import com.jenn.eventsinkorea.domain.admin.UserRepository;
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
    public String editSubmit(@Validated @ModelAttribute("user") Form form, BindingResult bindingResult, Integer id, RedirectAttributes redirectAttributes, Model model){
        log.info("controller access");
        if(bindingResult.hasErrors()){
            log.info("bindingResult={}",bindingResult);
            return "admin/users/edit";
        }

        //user_id 중복체크
        User byUser_idAndIdNot = userRepo.findByUserIdAndIdNot(form.getUserId(), id);
        if(byUser_idAndIdNot!=null){
            log.info("duplicate access");
//            bindingResult.addError(new FieldError("user", "userId", "Id already exists, choose another."));
            bindingResult.rejectValue("userId","userIdExists");
            log.info("bindingResult={}",bindingResult);
            return "admin/users/edit";
        }

        User userToBeEdited = userRepo.getById(id);
        userToBeEdited.setUserId(form.getUserId());
        userToBeEdited.setUsername(form.getUsername());
        userToBeEdited.setEmail(form.getEmail());
        userToBeEdited.setNationality(form.getNationality());

        userRepo.save(userToBeEdited);
        redirectAttributes.addFlashAttribute("edit","Edit Success");

        return "redirect:/admin/users/edit?id="+id;
    }

}
