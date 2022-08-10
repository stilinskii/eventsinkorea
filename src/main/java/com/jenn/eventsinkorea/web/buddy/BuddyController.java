package com.jenn.eventsinkorea.web.buddy;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.buddy.BuddyRepository;
import com.jenn.eventsinkorea.domain.buddy.BuddyService;
import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.web.buddy.form.BeABuddyForm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/buddy")
@RequiredArgsConstructor
public class BuddyController {

    private final BuddyService service;
    private final BuddyRepository buddyRepository;
    private final UserRepository userRepository;
    private final BeABuddyFormValidator buddyFormValidator;
    @GetMapping
    public String index(Model model){
        model.addAttribute("buddies", buddyRepository.findAll());

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

        service.saveBuddy(buddyForm);

        return "redirect:/buddy";
    }

    @GetMapping("/{buddyId}")
    public String buddyDetail(@PathVariable Long buddyId, Model model){
        Buddy buddy = buddyRepository.getById(buddyId);
        model.addAttribute("buddy",buddy);
        return "buddy/buddy";
    }
}
