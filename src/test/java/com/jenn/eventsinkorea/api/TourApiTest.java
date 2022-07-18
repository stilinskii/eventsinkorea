package com.jenn.eventsinkorea.api;


import com.jenn.eventsinkorea.domain.api.Event;
import com.jenn.eventsinkorea.domain.api.TourApi;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class TourApiTest {
    Logger log = (Logger) LoggerFactory.getLogger(TourApiTest.class);

    TourApi tourApi = new TourApi();

    @Test
    void getInfo(){
        List<Event> tourInfo = tourApi.getTourInfo();
        Assertions.assertThat(tourInfo).isNotEmpty();
    }

//    @Test
//    void parseDateTest(){
//        SimpleDateFormat format = new SimpleDateFormat("MMM'.' d'th'", Locale.ENGLISH);
//        Integer eventStartDate = 20220301;
//        Integer eventEndDate = 20220303;
//        String formattedEventStartDate;
//        String formattedEventEndDate;
//        try {
//            formattedEventStartDate = format.parse("Nov. 15th").toString();
//            formattedEventEndDate = format.parse(String.valueOf(eventEndDate)).toString();
//        } catch (java.text.ParseException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("result={},{}",formattedEventStartDate,formattedEventEndDate);
//    }


}
