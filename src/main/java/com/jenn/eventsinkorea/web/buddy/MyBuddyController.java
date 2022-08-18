package com.jenn.eventsinkorea.web.buddy;

import com.jenn.eventsinkorea.domain.buddy.BuddyService;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mybuddy")
@RequiredArgsConstructor
public class MyBuddyController {
    private final BuddyService buddyService;

    @GetMapping("/liked")
    public String buddyILikedPage(){
        return "mybuddy/buddyILiked";
    }

    @GetMapping("/sentRequest")
    public String sentRequestPage(Authentication auth, Model model){
        //로그인한 회원이 신청한 버디리퀘 목록
        List<BuddyRequest> requests = buddyService.getRequestByUsername(auth.getName());
        model.addAttribute("requests",requests);
        return "mybuddy/sentRequest";
    }

}
