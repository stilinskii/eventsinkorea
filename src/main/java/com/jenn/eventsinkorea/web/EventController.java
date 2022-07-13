package com.jenn.eventsinkorea.web;


import com.jenn.eventsinkorea.domain.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/festivals")
public class EventController {
    private final EventService eService;

    @GetMapping
    String festivals(Model model){
        model.addAttribute("eventsList",eService.getNotEndedFestivals());
        return "events";
    }

}
