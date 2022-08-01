package com.jenn.eventsinkorea.domain.event;

import com.jenn.eventsinkorea.domain.event.model.Event;
import com.jenn.eventsinkorea.domain.event.model.EventCommonInfo;
import com.jenn.eventsinkorea.domain.event.model.EventDetail;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class TourApiTest {
    private TourApi tourApi = new TourApi();



    private String contentId = tourApi.getRandomContentIdForTest();

    @Test
    void canGetEventsInfoFromAPI(){
        List<Event> events = tourApi.getEventsInfoFromAPI();

        assertThat(events).isNotNull();
    }


    @Test
    void getEventCommonInfo(){
        //given contentId
        //when
        EventCommonInfo eventDetail = tourApi.getEventCommonInfo(contentId);
        //then
        assertThat(eventDetail.getTitle()).isNotEmpty();

    }
    @Test
    void getEventDetail(){
        //given contentId
        //when
        EventDetail eventDetail = tourApi.getEventDetail(contentId);
        //then
        assertThat(eventDetail.getContentId()).isEqualTo(contentId);

    }

    @Test
    void getDetailImgs(){
        //given contentId
        //when
        List<String> subImgs = tourApi.getDetailImgs(contentId);
        //then
        assertThat(subImgs).isNotNull();

    }

}
