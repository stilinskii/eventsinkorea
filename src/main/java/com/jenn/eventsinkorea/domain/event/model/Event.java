package com.jenn.eventsinkorea.domain.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Event {
    //addr1  cat3  "contentid  eventenddate eventstartdate firstimage firstimage2 mapx": 127.2131685506,
    //"mapy": 37.2924319247, readcount tel  "title"

    private String id;//contentid
    private String title;//title
    private String address;//addr1 x
    private String category;//cat3 x
    private Integer eventStartDate; //eventstartdate
    private Integer eventEndDate; //eventenddate
    private String formattedEventStartDate;
    private String formattedEventEndDate;
    private List<String> imgs; //firstimage firstimage2
    private Map<String,Double> map;  //mapx": 127.2131685506, "mapy": 37.2924319247, x
    private Integer readcount;//readcount
    private String tel; //tel x

}
