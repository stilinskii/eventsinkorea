package com.jenn.eventsinkorea.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepo;

    public Integer getTodayJoinedUsersCnt(){
        List<User> users = userRepo.findAll();

        AtomicInteger cnt= new AtomicInteger();
        users.stream().forEach(user -> {
            Date join_date = user.getJoin_date();
            if(join_date==new Date()){
                cnt.getAndIncrement();
            }
        });
        return cnt.get();
    }

}
