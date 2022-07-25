package com.jenn.eventsinkorea.domain.api;


import com.jenn.eventsinkorea.domain.event.Event;
import com.jenn.eventsinkorea.domain.event.EventCommonInfo;
import com.jenn.eventsinkorea.domain.event.EventDetail;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
        public List<Event> getEventsInfoFromAPI() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String date = simpleDateFormat.format(new Date());
            String aYearAgoFromToday = String.valueOf(Integer.parseInt(date)-10000);

            List<Event> events = null;
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

                JSONArray itemArray = (JSONArray) (getItems(urlBuilder).get("item"));
                events = getEventsList(itemArray);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return events;
        }

        private JSONObject getItems(StringBuilder urlBuilder) {
            URL url;
            HttpURLConnection conn = null;
            BufferedReader rd = null;
            JSONObject items;
            try {
                url = new URL(urlBuilder.toString());
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
                items = (JSONObject) body.get("items");
            }catch(ClassCastException e){
                //items가 없을떄 이 에러가 남. 그럴땐 null 리턴
                items = null;
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    rd.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                conn.disconnect();
            }
            return items;
        }


    private List<Event> getEventsList(JSONArray item) {
        List<Event> festivals = new ArrayList<>();
        try {
            for (Object info : item) {
                JSONObject infoObj = (JSONObject) info;
                //이미지 없으면 패스
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
                List<String> mainImgs= getImgsList(infoObj);
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
                                        mainImgs, map, readcount, tel));


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

    private List<String> getImgsList(Object itemObj) {
        List<String> imgs = new ArrayList<>();
        if(itemObj instanceof JSONArray){
            JSONArray itemArray = (JSONArray) itemObj;
            for (Object o : itemArray) {
                JSONObject obj = (JSONObject) o;
                String detailImg = obj.get("originimgurl").toString();
                imgs.add(detailImg);
            }
        }else if(itemObj instanceof JSONObject){
            JSONObject item = (JSONObject) itemObj;
            imgs.add(item.get("firstimage").toString());
            imgs.add(item.get("firstimage2").toString());
        }

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

    public EventCommonInfo getEventCommonInfo(String contentId) {
        //http://api.visitkorea.or.kr/openapi/service/rest/EngService/detailCommon?&contentId=697135&overviewYN=Y&mapinfoYN=Y
        EventCommonInfo eventCommonInfo;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/EngService/detailCommon"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=YHl9nW394M7v47pQqImVXKdls5fjMA5tKRCD%2BZjjEFfHIWc%2BD6QKWxxmpManad2uIcE1b0Icw1AIhQcxDOUf7A%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰), AND(안드로이드), WIN(원도우폰), ETC*/
            urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
            urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + URLEncoder.encode(contentId, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("firstImageYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

            JSONObject items = getItems(urlBuilder);
            JSONObject item = (JSONObject) items.get("item");
            Map<String, Double> map = getMapList(item.get("mapx"), item.get("mapy"));
            String introduction = nullChk(item.get("overview"));
            String homepage = nullChk(item.get("homepage"));
            String address = item.get("addr1").toString();
            String title = item.get("title").toString();
            List<String> imgs = getImgsList(item);

            eventCommonInfo = new EventCommonInfo(map, introduction, homepage, address, title, imgs);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return eventCommonInfo;
    }

    public EventDetail getEventDetail(String contentId){
            EventDetail eventDetail;

        //http://api.visitkorea.or.kr/openapi/service/rest/EngService/detailIntro?&contentTypeId=85&contentId=1827088&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y
        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/EngService/detailIntro"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=YHl9nW394M7v47pQqImVXKdls5fjMA5tKRCD%2BZjjEFfHIWc%2BD6QKWxxmpManad2uIcE1b0Icw1AIhQcxDOUf7A%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode("85", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + URLEncoder.encode(contentId, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰), AND(안드로이드), WIN(원도우폰), ETC*/
            urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("TourAPI3.0_Guide", "UTF-8")); /*서비스명=어플명*/
            urlBuilder.append("&" + URLEncoder.encode("introYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

            JSONObject items = getItems(urlBuilder);
            JSONObject item = (JSONObject) items.get("item");

            String contentid = item.get("contentid").toString();
            String eventPlace = nullChk(item.get("eventplace")); // 해당사항 없으면 NPE 일어남.
            String tel = nullChk(item.get("sponsor1tel"));
            String sponsor = item.get("sponsor1").toString();
            String playtime = item.get("playtime").toString(); // 해당사항 없으면 이면 알아서 공백리턴해줌
            String admissionFee = item.get("usetimefestival").toString(); // 해당사항 없으면 이면 알아서 공백리턴해줌
            String program = item.get("program").toString(); // 해당사항 없으면 이면 알아서 공백리턴해줌
            String eventStartDate = item.get("eventstartdate").toString();
            String eventEndDate = item.get("eventenddate").toString();
            List<String> formattedEventPeriod = getFormattedEventPeriod(eventStartDate, eventEndDate);
            List<String> imgs = getDetailImgs(contentId);
            eventDetail = new EventDetail(getEventCommonInfo(contentId),
                                            contentid,
                                            eventPlace,
                                            tel,
                                            sponsor,
                                            playtime,
                                            admissionFee,
                                            program,
                                            formattedEventPeriod.get(0),
                                            formattedEventPeriod.get(1),
                                            imgs);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return eventDetail;
    }


    private String nullChk(Object itemField){
            if(itemField==null){
                return "";
            }else{
                return itemField.toString();
            }
    }

    public List<String> getDetailImgs(String contentId){
            //http://api.visitkorea.or.kr/openapi/service/rest/EngService/detailImage?serviceKey=YHl9nW394M7v47pQqImVXKdls5fjMA5tKRCD%2BZjjEFfHIWc%2BD6QKWxxmpManad2uIcE1b0Icw1AIhQcxDOUf7A%3D%3D&numOfRows=10&pageSize=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&contentId=1353950&imageYN=Y
        List<String> subImgs = new ArrayList<>();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/EngService/detailImage"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=YHl9nW394M7v47pQqImVXKdls5fjMA5tKRCD%2BZjjEFfHIWc%2BD6QKWxxmpManad2uIcE1b0Icw1AIhQcxDOUf7A%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과수*/
            urlBuilder.append("&" + URLEncoder.encode("pageSize", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰), AND(안드로이드), WIN(원도우폰), ETC*/
            urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
            urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + URLEncoder.encode(contentId, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode("85", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("imageYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

            JSONObject items = getItems(urlBuilder);
            //사진이 없을때
            if(items==null){
                return Collections.emptyList();
            }
            //사진이 1개일때.
            if(items.get("item") instanceof JSONObject){
                //바로 꺼내기
                subImgs.add(((JSONObject) items.get("item")).get("originimgurl").toString());
            }else{
                //사진이 여러개일때 ( 이 경우가 대부분 )
                JSONArray itemArray = (JSONArray) (items.get("item"));
                subImgs = getImgsList(itemArray);
            }


        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return subImgs;
    }




}

