package com.jenn.eventsinkorea.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetail {




    //공통정보
    private EventCommonInfo eventCommonInfo;
//    private Map<String,Double> map; // mapx , mapy
//    private String introduction; //overview
//    private String homepage;//homepage
    //    //대표 imgs
    //    //title
    //formattedEventStartDate endDate



    //소개정보 api
    private String contentId;
    private String eventPlace;//eventplace 행사장소
    private String tel; //sponsor1tel
    private String sponsor; //sponsor1
    private String playtime;// playtime
    private String admissionFee; //usetimefestival
    private String program; //program
    private String formattedEventStartDate;//eventstartdate
    private String formattedEventEndDate;//eventenddate

    //이미지들
    private List<String> imgs;




    //

}
