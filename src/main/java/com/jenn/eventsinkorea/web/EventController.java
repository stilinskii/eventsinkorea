package com.jenn.eventsinkorea.web;


import com.jenn.eventsinkorea.domain.api.Event;
import com.jenn.eventsinkorea.domain.api.EventDetail;
import com.jenn.eventsinkorea.domain.api.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "/events/events";
    }

    @GetMapping("/ongoing")
    public String onGoingEvents(Model model){
        List<Event> ongoingEvents = eService.getOngoingEvents();
        model.addAttribute("ongoingEvents","active");
        model.addAttribute("eventsList",ongoingEvents);
        return "/events/events";
    }

    @GetMapping("/ended")
    public String endedEvents(Model model){
        List<Event> endedEvents = eService.getEndedEvents();
        model.addAttribute("endedEvents","active");
        model.addAttribute("eventsList",endedEvents);
        return "events/events";
    }

    @PostMapping("/search")
    public String forSearchSubmit(String keyword, Model model){
        List<Event> eventsByKeyword = eService.getEventsByKeyword(keyword);
        model.addAttribute("keyword",keyword);
        model.addAttribute("eventsList",eventsByKeyword);
        return "events/events";
    }

    @GetMapping("/eventDetail")
    public String content(String contentId,Model model){
        EventDetail eventDetail = eService.getEventDetail(contentId);
        model.addAttribute("eventDetail",eventDetail);
        return "events/event";
    }



}
