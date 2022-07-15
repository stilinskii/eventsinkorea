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
        model.addAttribute("All","active");
        model.addAttribute("eventsList",eService.getEvents());
        log.info("infoCnt={}",eService.getEvents().size());
        return "events";
    }

    @GetMapping("/ongoing")
    String onGoingEvents(Model model){
        List<Event> ongoingEvents = eService.getOngoingEvents();
        model.addAttribute("ongoingEvents","active");
        model.addAttribute("eventsList",ongoingEvents);
        return "events";
    }

    @GetMapping("/ended")
    String endedEvents(Model model){
        List<Event> endedEvents = eService.getEndedEvents();
        model.addAttribute("endedEvents","active");
        model.addAttribute("eventsList",endedEvents);
        return "events";
    }




}
