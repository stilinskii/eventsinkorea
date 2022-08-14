package com.jenn.eventsinkorea.web;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(){
//        List<User> test = userRepository.findUsersByUsernameAndNationality("ha", null);
//        log.info("test={}",test.size());
        return "index";
    }
}
