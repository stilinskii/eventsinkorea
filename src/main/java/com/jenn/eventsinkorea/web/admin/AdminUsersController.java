package com.jenn.eventsinkorea.web.admin;

import com.jenn.eventsinkorea.domain.user.model.User;
import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.admin.AdminUserService;
import com.jenn.eventsinkorea.web.admin.form.UserEditForm;
import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;
import com.jenn.eventsinkorea.web.admin.validator.UserEditFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
public class AdminUsersController {

    private final UserRepository userRepository;
    private final AdminUserService userService;

    private final UserEditFormValidator userEditFormValidator;

//    @InitBinder
//    protected void initBinder(WebDataBinder binder){
//        binder.registerCustomEditor(Date.class);
//    }

    private UserSearchForm searchForm;

    @GetMapping
    public String users(Model model,
                        @PageableDefault(size = 2) Pageable pageable,
                        HttpServletRequest request){
        if(!request.getHeader("referer").contains("search")){
            searchForm = new UserSearchForm();
        }
//        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
//        if(inputFlashMap!=null && !inputFlashMap.isEmpty()){
//            searchForm = (UserSearchForm) inputFlashMap.get("searchForm");
//            log.info("here access");
//            log.info("searchForm={}",searchForm);
//        }

//        log.info("searchForm origin={}",searchForm);
        Page<User> users = userRepository.findUsersBySearchPage(searchForm,pageable);


        log.info("users={}",users.getTotalPages());
        int[] pageNum = pageNum(users);
        model.addAttribute("startPage",pageNum[0]);
        model.addAttribute("endPage",pageNum[1]);
        model.addAttribute("users", users);
        model.addAttribute("searchForm", searchForm);

        return "admin/users/index";
    }


    @PostMapping("/search")
    public String search(UserSearchForm form, Model model,RedirectAttributes redirectAttributes){
        searchForm = form;
//        redirectAttributes.addFlashAttribute("searchForm",form);

        return "redirect:/admin/users";
    }



    //index 0 - startNum / index 2 - endNum
    private int[] pageNum(Page<User> users) {
        int startPage,endPage;
        if(users.getPageable().getPageNumber()>= users.getTotalPages()-3 ){
            startPage= users.getTotalPages()-4;
            endPage= users.getTotalPages();
        }else{
            startPage= Math.max(1, users.getPageable().getPageNumber()-1);
            endPage= Math.min(startPage+4, users.getTotalPages());
        }

        return  new int[]  {startPage, endPage};
    }

    @GetMapping("edit")
    public String edit(Long id,Model model){
        User user = userRepository.getById(id);
        model.addAttribute("user",user);
        return "admin/users/edit";
    }


    //아이디를 꼭 넘겨야하나? 응. edit 할때 아이디중복확인때 필요함. 다른방법도있지만(같은아이디 2개인지확인) 굳이?
    @PostMapping("edit")
    public String editSubmit(@Validated @ModelAttribute("user") UserEditForm form, BindingResult bindingResult, Long id, RedirectAttributes redirectAttributes){
        userEditFormValidator.validate(form,bindingResult);
        if(bindingResult.hasErrors()){
            log.info("bindingResult={}",bindingResult);
            return "admin/users/edit";
        }

        //성공로직
        userService.editUserInfo(form, id);
        redirectAttributes.addFlashAttribute("edit","Edit Success");

        return "redirect:/admin/users/edit?id="+id;
    }

    @GetMapping("/delete")
    public String delete(Long id){
        userService.deleteUser(id);

        return "redirect:/admin/users";
    }




}
