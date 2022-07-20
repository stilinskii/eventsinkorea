package com.jenn.eventsinkorea.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCommonInfo {

    private Map<String,Double> map; // mapx , mapy
    private String introduction; //overview
    private String homepage;//homepage
    private String address;//attr1 주소

    //Event와겹치는게 좀 있어서 그걸 불러오려고 했지만
    //그렇게 되면 불러야하는 api url이 4개라서 로딩속도가 너무 느려질 거 같아서
    //api호출 하나라도 줄이기위해 중복되는 값을 다시 불러오게 되었다.
    private String title;

    //이미지조회 api
    private List<String> imgs;

}
