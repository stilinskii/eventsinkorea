package com.jenn.eventsinkorea.web;


import com.jenn.eventsinkorea.domain.api.Event;
import com.jenn.eventsinkorea.domain.api.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eService;


    @GetMapping("/All")
    public String eventsIndex(Model model){
        model.addAttribute("All","active");
        model.addAttribute("eventsList",eService.getEvents());
        log.info("infoCnt={}",eService.getEvents().size());
        return "/events/eventList";
    }

    @GetMapping("/ongoing")
    public String onGoingEvents(Model model){
        List<Event> ongoingEvents = eService.getOngoingEvents();
        model.addAttribute("ongoingEvents","active");
        model.addAttribute("eventsList",ongoingEvents);
        return "/events/eventList";
    }

    @GetMapping("/ended")
    public String endedEvents(Model model){
        List<Event> endedEvents = eService.getEndedEvents();
        model.addAttribute("endedEvents","active");
        model.addAttribute("eventsList",endedEvents);
        return "events/eventList";
    }

    @PostMapping("/search")
    public String forSearchSubmit(String keyword, Model model){
        List<Event> eventsByKeyword = eService.getEventsByKeyword(keyword);
        model.addAttribute("eventsList",eventsByKeyword);
        return "events/eventList";
    }

    @GetMapping
    public String content(String contentId){
        return "events/event";
    }



}
