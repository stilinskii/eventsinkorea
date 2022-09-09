package com.jenn.eventsinkorea.web.account;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.user.UserService;
import com.jenn.eventsinkorea.domain.user.model.User;
import com.jenn.eventsinkorea.web.account.form.UserLoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/signup")
    public String signUp(@ModelAttribute("form") UserLoginForm form){

        return "account/signup";
    }

    @PostMapping("/signup")
    public String signUpSumbit(@ModelAttribute("form") UserLoginForm form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
           return "account/signup";
        }
        log.info("form={}",form);


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
    public String mypageEdit(User user, Authentication auth, Model model){


        model.addAttribute("user",user);
        return "account/mypage";
    }
}
