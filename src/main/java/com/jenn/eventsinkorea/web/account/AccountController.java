package com.jenn.eventsinkorea.web.account;

import com.jenn.eventsinkorea.domain.user.User;
import com.jenn.eventsinkorea.domain.user.UserService;
import com.jenn.eventsinkorea.web.account.form.UserLoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserService UserService;

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

        User user = User.builder()
                        .username(form.getUsername())
                        .pwd(form.getPwd())
                        .name(form.getName())
                        .email(form.getEmail())
                        .nationality(form.getNationality())
                        .roles(new ArrayList<>())
                        .build();

        UserService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/mypage")
    public String mypage(){
        return "account/mypage";
    }
}
