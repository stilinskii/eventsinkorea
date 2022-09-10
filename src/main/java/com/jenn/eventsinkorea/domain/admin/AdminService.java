package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.user.model.User;
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


}
