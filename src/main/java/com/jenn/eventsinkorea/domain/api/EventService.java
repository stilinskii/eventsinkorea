package com.jenn.eventsinkorea.domain.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventService {

    private TourApi tourApi = new TourApi();


    public List<Event> getEvents(){
        return tourApi.getTourInfo();
    }





    // refactoring TODO
    //1년전부터 지금까지 시작한 행사들중 아직 종료하지 않은 행사들로 걸러내기
    public List<Event> getOngoingEvents(){
        List<Event> tourInfo = tourApi.getTourInfo();
        log.info("tourInfoCnt={}",tourInfo.size());
        //오늘날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        int today = Integer.parseInt(date);

        return tourInfo.stream()
                .filter(info -> Integer.parseInt(info.getEventEndDate()) >= today)
                .sorted(Comparator.comparing(Event::getEventStartDate))
                .collect(Collectors.toList());
    }


    public List<Event> getEndedEvents(){
        List<Event> tourInfo = tourApi.getTourInfo();
        log.info("tourInfoCnt={}",tourInfo.size());
        //오늘날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        int today = Integer.parseInt(date);

        return tourInfo.stream()
                .filter(info -> Integer.parseInt(info.getEventEndDate()) < today)
                .sorted(Comparator.comparing(Event::getEventStartDate))
                .collect(Collectors.toList());
    }

    //키워드로 검색 구현
    //앞뒤로 공백있으면 없애기 구현 TODO
    public List<Event> getEventsByKeyword(String keyword){
        List<Event> tourInfo = tourApi.getTourInfo();
       return tourInfo.stream()
               .filter(event -> event.getTitle().toLowerCase().contains(keyword.toLowerCase()))
               .collect(Collectors.toList());
    }

//나중에 쓸지도모름
//    public List<Event> getCategorizedEvents(String category){
//        List<Event> notEndedEvents = getNotEndedEvents();
//        List<Event> events = notEndedEvents.stream().filter(event -> event.getCategory().equals(category)).collect(Collectors.toList());
//        return events;
//    }


}
