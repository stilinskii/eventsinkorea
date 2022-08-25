package com.jenn.eventsinkorea.web.buddy;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.buddy.BuddyRepository;
import com.jenn.eventsinkorea.domain.buddy.BuddyRequestService;
import com.jenn.eventsinkorea.domain.buddy.BuddyService;
import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.user.User;
import com.jenn.eventsinkorea.web.ScriptUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mybuddy")
@RequiredArgsConstructor
public class MyBuddyController {
    private final BuddyService buddyService;
    private final BuddyRequestService buddyRequestService;
    private final UserRepository userRepository;
    private final BuddyRepository buddyRepository;

    private ScriptUtils scriptUtils = new ScriptUtils();

    @GetMapping
    public String buddyProfile(Authentication auth, Model model){
        Buddy buddy = buddyService.findBuddyByUsername(auth.getName());
        log.info("mybuddy access");
        log.info("buddy?={}",buddy);
        model.addAttribute("buddy",buddy);

        return "mybuddy/buddyProfile";
    }

    @GetMapping("/edit/{buddyId}")
    public String buddyProfileEdit(@PathVariable long buddyId, Model model){
//        Buddy buddy = buddyService.findBuddyByUsername(auth.getName());
        Buddy buddy = buddyRepository.getOne(buddyId);
        log.info("mybuddy access");
        log.info("buddyget={}",buddy);
        model.addAttribute("buddy",buddy);

        return "mybuddy/editBuddyForm";
    }

    @PostMapping("/edit")
    public String buddyProfileEditSubmit(Buddy buddy, MultipartFile image){
        log.info("buddypost={}",buddy);
        buddyService.saveBuddy(buddy,image);

        return "redirect:/mybuddy";
    }

    @GetMapping("/delete/{buddyId}")
    public String deleteBuddy(@PathVariable long buddyId){
        buddyService.deleteBuddy(buddyId);

        return "redirect:/mybuddy";
    }


    @GetMapping("/liked")
    public String buddyILikedPage(Authentication auth, Model model){
        User user = userRepository.findByUsername(auth.getName());
        List<Buddy> buddyILike = user.getBuddyILike();
        model.addAttribute("buddies",buddyILike);
        return "mybuddy/buddyILiked";
    }

    @GetMapping("/sent-request")
    public String sentRequestPage(Authentication auth, Model model){
        //로그인한 회원이 신청한 버디리퀘 목록
        List<BuddyRequest> requests = buddyRequestService.getSentRequestByUsername(auth.getName());
        model.addAttribute("requests",requests);
        return "mybuddy/sentRequest";
    }

    @GetMapping("/sent-request/delete/{buddyId}")
    public String deleteRequest(@PathVariable long buddyId, Authentication auth, HttpServletResponse response) throws IOException {
       buddyRequestService.deleteRequest(buddyId, auth.getName());
        ScriptUtils.alertAndBackPage(response,"cancel request success");

        return "redirect:/mybuddy/sent-request";
    }

    @GetMapping("/received-request")
    public String receivedRequestPage(Authentication auth, Model model){
        //로그인한 회원이 받은 버디리퀘 목록
        //대기중인거
        List<BuddyRequest> requests_waiting = buddyRequestService.getReceivedRequestByUsername(auth.getName(),true);
        //대기중 아닌거
        List<BuddyRequest> requests_history = buddyRequestService.getReceivedRequestByUsername(auth.getName(),false);
        model.addAttribute("requests",requests_waiting);
        model.addAttribute("requests_history",requests_history);
        return "mybuddy/receivedRequest";
    }


    @GetMapping("/sent-request/accept/{userid}")
    public String acceptRequest(@PathVariable Long userid, Authentication auth, HttpServletResponse response) throws IOException {
        //대기상태 리퀘 status 1로 바꾸기
        BuddyRequest buddyRequest = buddyRequestService.updateStatus(auth.getName(), userid, 1);
        //혹시 신청한 상대가 동시에 신청 취소할 경우를 대비.
        if (buddyRequest==null){
            ScriptUtils.alertAndBackPage(response,"accept fail");
        }else{
            ScriptUtils.alertAndBackPage(response,"accept success!");
        }

        return "redirect:/mybuddy/received-request";
    }

    @GetMapping("/sent-request/decline/{userid}")
    public String declineRequest(@PathVariable Long userid, Authentication auth, HttpServletResponse response) throws IOException {
        //대기상태 리퀘 status 2로 바꾸기
        BuddyRequest buddyRequest = buddyRequestService.updateStatus(auth.getName(), userid, 2);
        if (buddyRequest==null){
            ScriptUtils.alertAndBackPage(response,"refuse fail");
        }else{
            ScriptUtils.alertAndBackPage(response,"refuse success");
        }

        return "redirect:/mybuddy/received-request";
    }





}
