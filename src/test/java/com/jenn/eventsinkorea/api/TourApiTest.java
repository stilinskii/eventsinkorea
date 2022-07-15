package com.jenn.eventsinkorea.api;


import com.jenn.eventsinkorea.domain.api.Event;
import com.jenn.eventsinkorea.domain.api.TourApi;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;


public class TourApiTest {
    Logger log = (Logger) LoggerFactory.getLogger(TourApiTest.class);

    TourApi tourApi = new TourApi();

    @Test
    void getInfo(){
        List<Event> tourInfo = tourApi.getTourInfo();
        Assertions.assertThat(tourInfo).isNotEmpty();
    }


}
