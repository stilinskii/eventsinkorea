package com.jenn.eventsinkorea.web;


import com.jenn.eventsinkorea.domain.api.Event;
import com.jenn.eventsinkorea.domain.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eService;


    @GetMapping
    String eventsIndex(Model model){
        model.addAttribute("eventsList",eService.getNotEndedEvents());
        return "events";
    }

    @GetMapping("/festival")
    String festival(Model model){
        List<Event> festival = getCategorizedEvents("A0207");
        model.addAttribute("eventsList",festival);
        return "events";
    }

    @GetMapping("/showAndConcert")
    String showAndConcert(Model model){
        List<Event> showAndConcert = getCategorizedEvents("A0208");
        model.addAttribute("eventsList",showAndConcert);
        return "events";
    }

    private List<Event> getCategorizedEvents(String category) {
        List<Event> notEndedEvents = eService.getNotEndedEvents();
        //A0207  festival
        //A0208  show/concert
        List<Event> festival = notEndedEvents.stream().filter(event -> event.getCategory().equals(category)).collect(Collectors.toList());
        return festival;
    }


}
