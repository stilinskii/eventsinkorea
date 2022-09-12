package com.jenn.eventsinkorea;

import com.jenn.eventsinkorea.web.admin.TodayVisit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableScheduling
@Slf4j
@SpringBootApplication
@PropertySource("classpath:application.properties")
@PropertySource("classpath:aws.properties")
public class EventsinkoreaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsinkoreaApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	//방문자수 초기화
	@Scheduled(cron = "0 0 0 * * *")
	public void resetVisitCnt(){
		TodayVisit todayVisit = TodayVisit.getInstance();
		todayVisit.setTodayVisitCnt(0);
		log.info("===============reset VisitCnt executed");
	}
}
