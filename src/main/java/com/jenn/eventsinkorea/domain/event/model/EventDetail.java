package com.jenn.eventsinkorea.domain.event.model;

import com.jenn.eventsinkorea.domain.event.model.EventCommonInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetail {

    //공통정보
    private EventCommonInfo eventCommonInfo;


    //소개정보 api
    private String contentId;
    private String eventPlace;//eventplace 행사장소
    private String contact; //sponsor1tel
    private String sponsor; //sponsor1
    private String playtime;// playtime
    private String admissionFee; //usetimefestival
    private String program; //program
    private String formattedEventStartDate;//eventstartdate
    private String formattedEventEndDate;//eventenddate

    //이미지들
    private List<String> imgs;



}
