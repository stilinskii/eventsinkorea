package com.jenn.eventsinkorea.web.account;

import com.jenn.eventsinkorea.domain.admin.AdminUserService;
import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.user.UserService;
import com.jenn.eventsinkorea.domain.user.model.User;
import com.jenn.eventsinkorea.web.account.form.UserSignUpForm;
import com.jenn.eventsinkorea.web.admin.form.UserEditForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AdminUserService adminUserService;
    private final UserSignUpFormValidator userValidator;


    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/signup")
    public String signUp(@ModelAttribute("form") UserSignUpForm form){
        return "account/signup";
    }

    @PostMapping("/signup")
    public String signUpSumbit(@Validated @ModelAttribute("form") UserSignUpForm form, BindingResult bindingResult){
        userValidator.validate(form,bindingResult);
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
           return "account/signup";
        }

        userService.saveUser(form);
        return "redirect:/login";
    }

    @GetMapping("/mypage")
    public String mypage(Authentication auth, Model model){
        User user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user",user);
        return "account/mypage";
    }

    @PostMapping("/mypage")
    public String mypageEdit(@ModelAttribute("user") UserEditForm form, Long id, RedirectAttributes redirectAttributes){
        adminUserService.editUserInfo(form,id);
        redirectAttributes.addFlashAttribute("edit","Edit Success");
        return "redirect:/mypage";
    }
}
