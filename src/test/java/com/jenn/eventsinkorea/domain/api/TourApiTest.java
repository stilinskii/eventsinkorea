package com.jenn.eventsinkorea.domain.api;

import com.jenn.eventsinkorea.domain.event.EventDetail;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TourApiTest {
    TourApi tourApi = new TourApi();

    @Test
    void getEventDetail(){
        //given
        String contentId = "1827088";
        //when
        EventDetail eventDetail = tourApi.getEventDetail(contentId);
        //then
        assertThat(eventDetail.getContentId()).isEqualTo(contentId);

    }
}