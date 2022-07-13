package com.jenn.eventsinkorea.web;


import com.jenn.eventsinkorea.domain.api.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/festivals")
public class FestivalController {
    private final FestivalService fService;

    @GetMapping
    String festivals(Model model){
        model.addAttribute("festivalList",fService.getNotEndedFestivals());
        return "festivals";
    }

}
