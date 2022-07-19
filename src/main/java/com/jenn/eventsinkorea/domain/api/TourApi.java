package com.jenn.eventsinkorea.domain.api;


import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.json.simple.parser.ParseException;

@Slf4j
public class TourApi {

        //1년 전부터 앞으로의 행사정보 불러오기
        public List<Event> getTourInfo() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String date = simpleDateFormat.format(new Date());
            String aYearAgoFromToday = String.valueOf(Integer.parseInt(date)-10000);

            HttpURLConnection conn = null;
            BufferedReader rd = null;
            List<Event> festivals = null;
            try {
                StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/EngService/searchFestival"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=YHl9nW394M7v47pQqImVXKdls5fjMA5tKRCD%2BZjjEFfHIWc%2BD6QKWxxmpManad2uIcE1b0Icw1AIhQcxDOUf7A%3D%3D"); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("999", "UTF-8")); /*한 페이지 결과수*/
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
                urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰), AND(안드로이드), WIN(원도우폰), ETC*/
                urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
                urlBuilder.append("&" + URLEncoder.encode("listYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=" + URLEncoder.encode("O", "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("eventStartDate", "UTF-8") + "=" + URLEncoder.encode(aYearAgoFromToday, "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

                URL url = new URL(urlBuilder.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode());

                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }

                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(sb.toString());
                JSONObject response = (JSONObject) obj.get("response");
                JSONObject body = (JSONObject) response.get("body");
                JSONObject items = (JSONObject) body.get("items");
                JSONArray item = (JSONArray) items.get("item");

                festivals = getFestivalsList(item);


            } catch (IOException | ParseException e) {
                e.printStackTrace();
            } finally {
                try {
                    rd.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                conn.disconnect();
            }
            return festivals;
        }

    private List<Event> getFestivalsList(JSONArray item) {
        List<Event> festivals = new ArrayList<>();
        try {
            for (Object info : item) {
                JSONObject infoObj = (JSONObject) info;
                if(infoObj.get("firstimage")==null){
                    continue;
                }
                //객체필드
                String id = infoObj.get("contentid").toString();
                String title = infoObj.get("title").toString();
                String address = infoObj.get("addr1").toString();
                String category = infoObj.get("cat2").toString();//fesitval or show/concert
                Integer eventStartDate = Integer.valueOf(infoObj.get("eventstartdate").toString());
                Integer eventEndDate = Integer.valueOf(infoObj.get("eventenddate").toString());
                List<String> formattedEventPeriod = getFormattedEventPeriod(infoObj.get("eventstartdate").toString(), infoObj.get("eventenddate").toString());// [0] startdate, [1] enddate
                List<String> imgs=getImgList(infoObj.get("firstimage"), infoObj.get("firstimage2"));
                Map<String, Double> map =getMapList(infoObj.get("mapx"), infoObj.get("mapy"));
                Integer readcount = Integer.parseInt(infoObj.get("readcount").toString());
                String tel = infoObj.get("tel").toString();

                festivals.add(new Event(id,
                                        title,
                                        address,
                                        category,
                                        eventStartDate,
                                        eventEndDate,
                                        formattedEventPeriod.get(0),
                                        formattedEventPeriod.get(1),
                                        imgs, map, readcount, tel));


            }

        } catch (NullPointerException e) {
            e.printStackTrace();

        }

        return festivals;
    }

    public List<String> getFormattedEventPeriod(String ...eventPeriodDates) {
        //분명 더 좋은 방법이 있을 거 같은데..
        List<String> formattedEventDates = new ArrayList<>();
        Arrays.stream(eventPeriodDates).forEach(d->{
            int year = Integer.parseInt(d.substring(0,4));
            int month = Integer.parseInt(d.substring(4,6));
            int day = Integer.parseInt(d.substring(6,8));
            LocalDate date = LocalDate.of(year, month, day);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM'.' d'th' yyyy"));
            formattedEventDates.add(formattedDate);
        });

        return formattedEventDates;
    }

    private List<String> getImgList(Object ...img) {
        List<String> imgs = new ArrayList<>();
        Arrays.stream(img).forEach(m ->{
            imgs.add(m.toString());
        });
        return imgs;
    }

    private Map<String,Double> getMapList(Object mapx,Object mapy) {
        //지도 정보가 없는 축제가 있어서 널처리. x가 없으면 y도 없음.
        Map<String,Double> map = new HashMap<>();
        if(mapx!=null){
        map.put("mapx",Double.parseDouble(mapx.toString()));
        map.put("mapy",Double.parseDouble(mapy.toString()));
        }
        return map;
    }



}

