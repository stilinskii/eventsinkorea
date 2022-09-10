package com.jenn.eventsinkorea.domain;

import com.jenn.eventsinkorea.web.admin.TodayVisit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Slf4j
public class VisitorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        Cookie visitor = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("visitor")).findAny().orElse(null);

        TodayVisit todayVisit = TodayVisit.getInstance();
        if(visitor==null){
            Cookie cookie = new Cookie("visitor", "visitor");
            //cookie.setMaxAge(60 * 60 * 24); // 24시간 유지, 음수일 경우 브라우저 종료시까지 유지
            cookie.setPath("/"); // 하위 경로 전체에 쿠키 적용
            response.addCookie(cookie);

            todayVisit.addVisitCnt();
        }else{
            //visitor쿠키가 남아있는 상태에서 다음날이 되어 방문자수가 초기화됐을때 기존 방문하고 있는 방문자를 다음날의 방문자로 추가한다.
            if(todayVisit.getTodayVisitCnt()==0){
                todayVisit.addVisitCnt();
            }
        }

        return true;
    }
}
