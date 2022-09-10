package com.jenn.eventsinkorea.web.admin;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class TodayVisit {
    private int todayVisitCnt;

    private static TodayVisit todayVisit = new TodayVisit();

    private TodayVisit() {}

    public static TodayVisit getInstance(){
        return todayVisit;
    }

    public int getTodayVisitCnt() {
        return todayVisitCnt;
    }

    public void setTodayVisitCnt(int todayVisitCnt) {
        this.todayVisitCnt = todayVisitCnt;
    }

    public void addVisitCnt(){
        setTodayVisitCnt(todayVisitCnt+1);
    }


}
