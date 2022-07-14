package com.jenn.eventsinkorea.web;


import com.jenn.eventsinkorea.domain.api.Event;
import com.jenn.eventsinkorea.domain.api.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.jenn.eventsinkorea.domain.api.ApiCategoryConst.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eService;


    @GetMapping
    String eventsIndex(Model model){
        model.addAttribute("eventsList",eService.getNotEndedEvents());
        log.info("infoCnt={}",eService.getNotEndedEvents().size());
        return "events";
    }

    @GetMapping("/festival")
    String festival(Model model){
        List<Event> festival = eService.getCategorizedEvents(FESTIVAL);
        model.addAttribute("eventsList",festival);
        return "events";
    }

    @GetMapping("/showAndConcert")
    String showAndConcert(Model model){
        List<Event> showAndConcert = eService.getCategorizedEvents(SHOW_CONCERT);
        model.addAttribute("eventsList",showAndConcert);
        return "events";
    }




}
