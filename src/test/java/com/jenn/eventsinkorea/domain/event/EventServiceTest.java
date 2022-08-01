package com.jenn.eventsinkorea.domain.event;

import com.jenn.eventsinkorea.domain.event.model.Event;
import com.jenn.eventsinkorea.domain.event.model.EventDetail;
import org.junit.jupiter.api.Test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



class EventServiceTest {

    private EventService underTest = new EventService();

    private TourApi tourApi = new TourApi();


    @Test
    void getEvents() {
        //given
        //when
        List<Event> events = underTest.getEvents();
        //then
        //행사시작일 기준으로 오름차순 정렬확인
        assertThat(events.get(0).getEventStartDate()).isLessThanOrEqualTo(events.get(1).getEventStartDate());
        assertThat(events.get(0).getEventStartDate()).isLessThanOrEqualTo(events.get(2).getEventStartDate());
    }

    @Test
    void getOngoingEvents() {
        //given
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        int today = Integer.parseInt(date);

        //when
        List<Event> ongoingEvents = underTest.getOngoingEvents();

        //then
        //결과값이 아직 종료하지 않은 행사들인지 확인
        ongoingEvents.forEach(event -> {
            assertThat(event.getEventEndDate()).isGreaterThanOrEqualTo(today);
        });
    }

    @Test
    void getEndedEvents() {
        //given
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        int today = Integer.parseInt(date);

        //when
        List<Event> endedEvents = underTest.getEndedEvents();

        //then
        //아직 종료하지 않은 행사들인지 확인
        endedEvents.forEach(event -> {
            assertThat(event.getEventEndDate()).isLessThan(today);
        });
    }

    @Test
    void getEventsByOneKeyword() {
        //given
        String keyword="서울";
        //when
        List<Event> eventsByKeyword = underTest.getEventsByKeyword(keyword);
        //then
        if(eventsByKeyword.size()>0){
        eventsByKeyword.forEach(event -> {
            assertThat(event.getTitle()).contains(keyword);
        });
        }
    }

    @Test
    void getEventsBySeveralKeywords(){
        //given
        String keyword="서울 부산";
        String[] keywords = keyword.split(" ");
        //when
        List<Event> eventsByKeyword = underTest.getEventsByKeyword(keyword);
        //then
        if(eventsByKeyword.size()>0){
            eventsByKeyword.forEach(event -> {
                assertThat(event.getTitle()).containsAnyOf(keywords);
            });
        }
    }

    @Test
    void canNotGetEventsBySeveralKeywords(){
        //given
        String keyword="ㄴㅇㄻㄴㅇ";
        //when
        List<Event> eventsByKeyword = underTest.getEventsByKeyword(keyword);
        //then
        assertThat(eventsByKeyword.size()).isEqualTo(0);
    }


    @Test
    void getEventDetail() {
        //given
        String contentId = tourApi.getRandomContentIdForTest();
        //when
        EventDetail eventDetail = underTest.getEventDetail(contentId);
        //then
        //대표이미지가 2장이고 서브이미지는 있을때도있고 없을떄도 있어서 사진은 적어도 2장이 있어야함.
        assertThat(eventDetail.getImgs().size()).isGreaterThanOrEqualTo(2);
    }
}