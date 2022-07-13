package com.jenn.eventsinkorea.domain.api;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FestivalService {

    private TourApi tourApi = new TourApi();

    //올해 시작한 행사들중 아직 종료하지 않은 행사들로 걸러내기
    public List<Festival> getNotEndedFestivals(){
        List<Festival> tourInfo = tourApi.getTourInfo();
        //오늘날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        int today = Integer.parseInt(date);

        return tourInfo.stream()
                .filter(info -> Integer.parseInt(info.getEventEndDate()) >= today)
                .collect(Collectors.toList());
    }

}
