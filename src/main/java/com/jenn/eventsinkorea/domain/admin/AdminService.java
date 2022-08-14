package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.user.User;
import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final UserRepository userRepo;

    //보류 ^^ TODO
    public Integer getTodayJoinedUsersCnt(){
        List<User> users = userRepo.findAll();

        AtomicInteger cnt= new AtomicInteger();
        users.stream().forEach(user -> {
            Date join_date = user.getJoinedDate();
            if(join_date==new Date()){
                cnt.getAndIncrement();
            }
        });

//        List<User> users2 = userRepo.findByCreatedAtAfter( new java.sql.Date(System.currentTimeMillis()));
//        Date joinDate = userRepo.findAll().get(0).getJoinDate();
//        log.info("user2 size={}",users2.size());
//       log.info("date={}",joinDate);
//        log.info("date={}",new Date());
        // Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        //        List<Apple> findByExpiryDateBefore =
        //        appleRepository.findByExpiryDateBefore(new java.sql.Date(expiryDate.getTime()));

        return cnt.get();
    }

}
