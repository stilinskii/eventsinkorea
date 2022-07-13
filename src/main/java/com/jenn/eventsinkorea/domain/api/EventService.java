package com.jenn.eventsinkorea.domain.api;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private TourApi tourApi = new TourApi();

    //올해 시작한 행사들중 아직 종료하지 않은 행사들로 걸러내기
    public List<Event> getNotEndedEvents(){
        List<Event> tourInfo = tourApi.getTourInfo();
        //오늘날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        int today = Integer.parseInt(date);

        return tourInfo.stream()
                .filter(info -> info.getEventEndDate() >= today)
                .sorted(Comparator.comparing(Event::getEventStartDate))
                .collect(Collectors.toList());
    }



}
