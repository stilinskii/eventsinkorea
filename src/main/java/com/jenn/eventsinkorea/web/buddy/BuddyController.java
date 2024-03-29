package com.jenn.eventsinkorea.web.buddy;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.buddy.repository.BuddyRepository;
import com.jenn.eventsinkorea.domain.buddy.repository.BuddyReviewRepository;
import com.jenn.eventsinkorea.domain.buddy.BuddyService;
import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyReview;
import com.jenn.eventsinkorea.domain.user.model.User;
import com.jenn.eventsinkorea.web.ScriptUtils;
import com.jenn.eventsinkorea.web.buddy.form.BeABuddyForm;
import com.jenn.eventsinkorea.web.buddy.form.BuddyFilteringSortingOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

    private final BuddyReviewRepository buddyReviewRepository;

    private final BeABuddyFormValidator buddyFormValidator;

    private BuddyFilteringSortingOption option;

    private ScriptUtils scriptUtils = new ScriptUtils();


    @GetMapping
    public String index(@PageableDefault(size = 3) Pageable pageable, Model model, Authentication auth){
        //사용자가 좋아요한 버디목록 넘겨서 좋아요 하트 채우기
        //로그인 안했을땐 빈 리스트 넘기기.
        List<Integer> userLikedBuddyIds = new ArrayList<>();
        if(auth!=null){
            User user = userRepository.findByUsername(auth.getName());
            List<Buddy> buddyILike = user.getBuddyILike();
            userLikedBuddyIds = buddyILike.stream().map(Buddy::getId).map(Long::intValue).collect(Collectors.toList());

        }
        option = new BuddyFilteringSortingOption();
        Slice<Buddy> indexBuddy = buddyService.getFilteredBuddies(option,pageable);
        model.addAttribute("buddies", indexBuddy);
        model.addAttribute("buddyIds",userLikedBuddyIds);
        return "buddy/buddies";
    }


    @PostMapping("/filtering")
    public String buddyFiltering(BuddyFilteringSortingOption inputtedOption, Model model, Authentication auth, @PageableDefault(size = 3) Pageable pageable){
        option = BuddyFilteringSortingOption.builder()
                .nativeLang(inputtedOption.getNativeLang())
                .learningLang(inputtedOption.getLearningLang())
                .location(inputtedOption.getLocation())
                .sorting(inputtedOption.getSorting()).build();



        Slice<Buddy> buddies = buddyService.getFilteredBuddies(inputtedOption,pageable);
        List<Integer> userLikedBuddyIds = new ArrayList<>();
        if(auth!=null){
            User user = userRepository.findByUsername(auth.getName());
            List<Buddy> buddyILike = user.getBuddyILike();
            userLikedBuddyIds = buddyILike.stream().map(Buddy::getId).map(Long::intValue).collect(Collectors.toList());
        }

        if(!buddies.hasNext()){
            model.addAttribute("noMore",true);

        }

        model.addAttribute("buddyIds",userLikedBuddyIds);
        model.addAttribute("buddies", buddies);
        return "buddy/buddies :: #buddies";
    }


    //ajax 페이지 추가.
    @GetMapping("/buddypage")
    public String more(@PageableDefault(size = 3) Pageable pageable, Authentication auth, Model model){

        Slice<Buddy> buddies = buddyService.getFilteredBuddies(option,pageable);

        //다음 페이지가 없을때 more버튼 숨기기 위해 정보 보내기
        if(!buddies.hasNext()){
            model.addAttribute("noMore",true);
        }

        List<Integer> userLikedBuddyIds = new ArrayList<>();
        if(auth!=null){
            User user = userRepository.findByUsername(auth.getName());
            List<Buddy> buddyILike = user.getBuddyILike();
            userLikedBuddyIds = buddyILike.stream().map(Buddy::getId).map(Long::intValue).collect(Collectors.toList());
        }
        model.addAttribute("buddyIds",userLikedBuddyIds);
        model.addAttribute("buddies", buddies);

        return "buddy/buddyPart";
    }


    @GetMapping("/beABuddy")
    public String beABuddy(@ModelAttribute("buddyForm") BeABuddyForm buddyForm, Authentication auth){

        return "buddy/beABuddyForm";
    }

    @PostMapping("/beABuddy")
    public String forSubmitOfBeABuddy(@Validated  @ModelAttribute("buddyForm") BeABuddyForm buddyForm,
                                      BindingResult bindingResult, Authentication auth){
        buddyFormValidator.validate(buddyForm,bindingResult);
        if(bindingResult.hasErrors()){
            return "buddy/beABuddyForm";
        }
        buddyForm.setUsername(auth.getName());
        buddyService.saveBuddy(buddyForm);
        return "redirect:/buddy";
    }

    @GetMapping("/{buddyId}")
    public String buddyDetail(@PathVariable Long buddyId, Model model, Authentication auth){
        if(auth!=null){
            User user = userRepository.findByUsername(auth.getName());
            boolean isLikedBuddy = user.getBuddyILike().contains(buddyRepository.getById(buddyId));
            model.addAttribute("isLikedBuddy",isLikedBuddy);
        }

        Buddy buddy = buddyRepository.getById(buddyId);
        List<BuddyReview> reviews =buddyReviewRepository.findByBuddyOrderByCreatedAtDesc(buddy);
        model.addAttribute("reviewCnt",buddyReviewRepository.countReviewByBuddyId(buddyId));
        model.addAttribute("buddy",buddy);
        model.addAttribute("reviews",reviews);
        return "buddy/buddy";
    }


    @GetMapping("/request/{buddyId}")
    public String buddyRequest(HttpServletRequest request,@PathVariable Long buddyId,Authentication auth,HttpServletResponse response) throws IOException {

        BuddyRequest buddyRequest = buddyService.requestBuddy(auth.getName(), buddyId);
        String referer = request.getHeader("referer");

        if(buddyRequest==null){
            scriptUtils.alertAndBackPage(response,"you have already requested.");
        }

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
