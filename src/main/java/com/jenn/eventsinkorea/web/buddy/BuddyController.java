package com.jenn.eventsinkorea.web.buddy;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.buddy.BuddyRepository;
import com.jenn.eventsinkorea.domain.buddy.BuddyService;
import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.user.User;
import com.jenn.eventsinkorea.web.buddy.form.BeABuddyForm;
import com.jenn.eventsinkorea.web.buddy.form.BuddyFilteringOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/buddy")
@RequiredArgsConstructor
public class BuddyController {

    private final BuddyService buddyService;

    private final UserRepository userRepository;

    private final BuddyRepository buddyRepository;

    private final BeABuddyFormValidator buddyFormValidator;


    @GetMapping
    public String index(Model model, Authentication auth){
        //로그인한 유저가 좋아요한 버디의 아이디 가져오기

        //로그인 안했을땐 빈 리스트 넘기기.
        List<Integer> userLikedBuddyIds = Collections.EMPTY_LIST;
        if(auth!=null){
            User user = userRepository.findByUsername(auth.getName());
            List<Buddy> buddyILike = user.getBuddyILike();
            userLikedBuddyIds = buddyILike.stream().map(Buddy::getId).map(Long::intValue).collect(Collectors.toList());
        }

        model.addAttribute("buddies", buddyRepository.findAll());
        model.addAttribute("buddyIds",userLikedBuddyIds);

        return "buddy/buddies";
    }

    @GetMapping("/beABuddy")
    public String beABuddy(@ModelAttribute("buddyForm") BeABuddyForm buddyForm){
        return "buddy/beABuddyForm";
    }

    @PostMapping("/beABuddy")
    public String forSubmitOfBeABuddy(@Validated  @ModelAttribute("buddyForm") BeABuddyForm buddyForm,
                                      BindingResult bindingResult){
        buddyFormValidator.validate(buddyForm,bindingResult);
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "buddy/beABuddyForm";
        }
        buddyService.saveBuddy(buddyForm);
        return "redirect:/buddy";
    }

    @GetMapping("/{buddyId}")
    public String buddyDetail(@PathVariable Long buddyId, Model model, Authentication auth){
        if(auth!=null){
            User user = userRepository.findByUsername(auth.getName());
            boolean isLikedBuddy = user.getBuddyILike().contains(buddyId);
            model.addAttribute("buddyILiked",isLikedBuddy);
        }
        Buddy buddy = buddyRepository.getById(buddyId);
        model.addAttribute("buddy",buddy);
        return "buddy/buddy";
    }

    //ajax
    @PostMapping("/filtering")
    public String buddyFiltering(BuddyFilteringOption buddyFiltering, Model model){
        log.info("buddyFiltering={}",buddyFiltering);
        List<Buddy> buddies = buddyService.getFilteredbuddies(buddyFiltering);
        model.addAttribute("buddies", buddies);
        return "buddy/buddies :: #buddies";
    }

    @GetMapping("/request/{buddyId}")
    public String buddyRequest(HttpServletRequest request,@PathVariable Long buddyId,Authentication auth){
        log.info("auth test={}",auth.getName());
        BuddyRequest buddyRequest = buddyService.saveRequest(auth.getName(), buddyId);

        String referer = request.getHeader("referer");
        return "redirect:"+referer;
    }

    @Secured("Role_User")
    @ResponseBody
    @PostMapping("/like")
    public Long updateLikeCnt(Long buddyId, boolean add, Authentication auth){
        if(add==true){
            buddyService.addLikeCnt(buddyId,auth.getName());
        }else{
            buddyService.subtractLikeCnt(buddyId,auth.getName());
        }
        Long updatedLikeCnt = buddyRepository.getOne(buddyId).getLikeCnt();

        return updatedLikeCnt;
    }
}
