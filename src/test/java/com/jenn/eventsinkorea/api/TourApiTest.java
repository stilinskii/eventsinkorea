package com.jenn.eventsinkorea.api;


import com.jenn.eventsinkorea.domain.api.Event;
import com.jenn.eventsinkorea.domain.api.TourApi;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


public class TourApiTest {
    TourApi tourApi = new TourApi();

    @Test
    void getInfo() throws IOException {
        List<Event> tourInfo = tourApi.getTourInfo();
        Assertions.assertThat(tourInfo).isNotEmpty();
    }
}
