package com.jenn.eventsinkorea.web.buddy;

import com.jenn.eventsinkorea.domain.buddy.BuddyRepository;
import com.jenn.eventsinkorea.domain.buddy.BuddyService;
import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mybuddy")
@RequiredArgsConstructor
public class MyBuddyController {
    private final BuddyService buddyService;
    private final BuddyRepository buddyRepository;

    @GetMapping
    public String buddyProfile(Authentication auth, Model model){
        Buddy buddy = buddyService.findBuddyByUsername(auth.getName());
        log.info("mybuddy access");
        log.info("buddy?={}",buddy);
        model.addAttribute("buddy",buddy);

        return "mybuddy/buddyProfile";
    }

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

    @GetMapping("/edit/{buddyId}")
    public String buddyProfileEdit(@PathVariable long buddyId, Authentication auth, Model model){
//        Buddy buddy = buddyService.findBuddyByUsername(auth.getName());
        Buddy buddy = buddyRepository.getOne(buddyId);
        log.info("mybuddy access");
        log.info("buddyget={}",buddy);
        model.addAttribute("buddy",buddy);

        return "mybuddy/editBuddyForm";
    }

    @PostMapping("/edit")
    public String buddyProfileEditSubmit( Buddy buddy, MultipartFile image){

        log.info("buddypost={}",buddy);
        buddyService.saveBuddy(buddy,image);

        return "redirect:/mybuddy";
    }

}
