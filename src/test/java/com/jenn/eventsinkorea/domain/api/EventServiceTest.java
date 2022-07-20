package com.jenn.eventsinkorea.domain.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class EventServiceTest {

    EventService service = new EventService();


    @Test
    void getEventsByKeyword(){
        String searchKeyword = "무예";
        List<Event> searchedList = service.getEventsByKeyword(searchKeyword);
        for (Event event : searchedList) {
            Assertions.assertThat(event.getTitle().contains(searchKeyword));
        }
    }


}