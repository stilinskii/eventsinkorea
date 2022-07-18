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

    //행사시작일 기준으로 오름차순 정렬
    public List<Event> getEvents(){
        return tourApi.getTourInfo().stream().sorted(Comparator.comparing(Event::getEventStartDate))
                .collect(Collectors.toList());
    }


    //1년전부터 지금까지 시작한 행사들중 아직 종료하지 않은 행사들로 걸러내기
    public List<Event> getOngoingEvents(){
        List<Event> tourInfo = getEvents();
        log.info("tourInfoCnt={}",tourInfo.size());
        int today = getToday();

        return tourInfo.stream()
                .filter(info -> info.getEventEndDate() >= today)
                .collect(Collectors.toList());
    }

    //1년전부터 지금까지 시작한 행사들중 이미 종료한 행사들
    public List<Event> getEndedEvents(){
        List<Event> tourInfo = getEvents();
        log.info("tourInfoCnt={}",tourInfo.size());
        //오늘날짜
        int today = getToday();

        return tourInfo.stream()
                .filter(info -> info.getEventEndDate() < today)
                .collect(Collectors.toList());
    }

    //오늘날짜 숫자로
    private int getToday() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        int today = Integer.parseInt(date);
        return today;
    }

    //키워드로 검색 구현
    public List<Event> getEventsByKeyword(String keyword){
        List<Event> tourInfo = getEvents();
        String strippedKeyword = keyword.strip();
       return tourInfo.stream()
               .filter(event -> event.getTitle().toLowerCase().contains(strippedKeyword.toLowerCase()))
               .collect(Collectors.toList());
    }

//나중에 쓸지도모름
//    public List<Event> getCategorizedEvents(String category){
//        List<Event> notEndedEvents = getNotEndedEvents();
//        List<Event> events = notEndedEvents.stream().filter(event -> event.getCategory().equals(category)).collect(Collectors.toList());
//        return events;
//    }


}
