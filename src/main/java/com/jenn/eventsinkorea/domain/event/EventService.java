package com.jenn.eventsinkorea.domain.event;

import com.jenn.eventsinkorea.domain.event.api.TourApi;
import com.jenn.eventsinkorea.domain.event.model.Event;
import com.jenn.eventsinkorea.domain.event.model.EventDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class EventService {

    private TourApi tourApi = new TourApi();


    //행사시작일 기준으로 오름차순 정렬
    public List<Event> getEvents(){
        return tourApi.getEventsInfoFromAPI().stream().sorted(Comparator.comparing(Event::getEventStartDate))
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

        //두 단어 이상 검색했을 경우
        if(strippedKeyword.contains(" ")){
            List<Event> filteredInfo = new ArrayList<>();
            String[] keywords = strippedKeyword.split(" ");
            for (String s : keywords) {
                tourInfo.stream().forEach(event -> {
                    if(event.getTitle().contains(s)){
                        filteredInfo.add(event);
                    }
                });
            }
            return filteredInfo.stream().distinct().collect(Collectors.toList());
        }

        //단어 그냥 하나일 경우
       return tourInfo.stream()
               .filter(event -> event.getTitle().toLowerCase().contains(strippedKeyword.toLowerCase()))
               .collect(Collectors.toList());
    }


    public EventDetail getEventDetail(String contentId){
        EventDetail eventDetail = tourApi.getEventDetail(contentId);
        //대표이미지와 서브이미지들을 합쳐서 eventDetail에 담기.
        List<String> mainImgs = eventDetail.getEventCommonInfo().getImgs();
        List<String> subImgs = eventDetail.getImgs();
        List<String> imgs = Stream.concat(mainImgs.stream(), subImgs.stream())
                .collect(Collectors.toList());
        eventDetail.setImgs(imgs);
        return eventDetail;
    }

//나중에 쓸지도모름
//    public List<Event> getCategorizedEvents(String category){
//        List<Event> notEndedEvents = getNotEndedEvents();
//        List<Event> events = notEndedEvents.stream().filter(event -> event.getCategory().equals(category)).collect(Collectors.toList());
//        return events;
//    }


}
