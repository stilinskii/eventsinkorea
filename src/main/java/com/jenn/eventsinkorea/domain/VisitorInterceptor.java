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
            //cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/");
            response.addCookie(cookie);

            todayVisit.addVisitCnt();
        }

        return true;
    }
}
