package com.jenn.eventsinkorea.web.buddy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buddy")
public class BuddyController {

    @GetMapping
    public String index(){
        return "buddy/index";
    }
}
