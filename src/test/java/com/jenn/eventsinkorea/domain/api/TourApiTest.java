package com.jenn.eventsinkorea.domain.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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