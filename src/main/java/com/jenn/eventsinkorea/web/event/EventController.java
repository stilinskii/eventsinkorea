package com.jenn.eventsinkorea.web.event;


import com.jenn.eventsinkorea.domain.event.Event;
import com.jenn.eventsinkorea.domain.event.EventDetail;
import com.jenn.eventsinkorea.domain.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eService;


    @GetMapping("/All")
    public String eventsIndex(Model model, HttpSession session){
        model.addAttribute("All","active");
        model.addAttribute("eventsList",eService.getEvents());
        log.info("infoCnt={}",eService.getEvents().size());

        if(session.getAttribute("recentlyViewed")!=null){
            HashMap<String,RecentlyViewed> recentlyViewed = (HashMap<String, RecentlyViewed>) session.getAttribute("recentlyViewed");
            model.addAttribute("recentlyViewed",recentlyViewed);
            log.info("recentlyView={}",recentlyViewed.toString());
        }
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
    public String content(String contentId, Model model, HttpSession session){
        EventDetail eventDetail = eService.getEventDetail(contentId);
        model.addAttribute("eventDetail",eventDetail);
        //session에 RecentlyViewed 에 넣기. 최대 5개.
        //LinkedHashMap사용  삽입순서, 중복안됨이어야해서.
        //문제 : 새로 삽입된게 맨밑으로 안옴. 결국 있는거 지우고 다시 삽입
        LinkedHashMap<String, RecentlyViewed> recentlyViewed;

        //ArrayList<RecentlyViewed> recentlyViewed;
        if(session.getAttribute("recentlyViewed")==null){
            recentlyViewed = new LinkedHashMap<>();
            recentlyViewed.put(contentId,new RecentlyViewed(eventDetail.getEventCommonInfo().getTitle(),eventDetail.getEventCommonInfo().getImgs().get(0)));

        }else{
            //이미 recentlyViewed가 있을때
            recentlyViewed = (LinkedHashMap<String, RecentlyViewed>) session.getAttribute("recentlyViewed");

            //해당 아이디로된 데이터 있다면 그거 지움 그리고 마지막 인덱스로 지금꺼 넣음.(순서 바꾸기 위해)
            if(recentlyViewed.containsKey(contentId)){
                recentlyViewed.remove(contentId);
            }
            recentlyViewed.put(contentId,new RecentlyViewed(eventDetail.getEventCommonInfo().getTitle(),eventDetail.getEventCommonInfo().getImgs().get(0)));
        }


        session.setAttribute("recentlyViewed",recentlyViewed);


        return "events/event";
    }



}
