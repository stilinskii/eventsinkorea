package com.jenn.eventsinkorea.web;

public class PagesController {

    //    @GetMapping("/{slug}")
    //    public String page(@PathVariable String slug,Model model){
    //        Page page = pageRepo.findBySlug(slug);
    //        if(page==null){
    //            return "redirect:/";
    //        }
    //        model.addAttribute("page",page);
    //        return "page";
    //    }

    // slug받고 해당 slug 홈페이지로 redirect.
    //response entity로 해당 페이지 response code
    //response code가 정상적이지 않으면 설명페이지(page.html)로 이동
    //page자체가 존재하지 않으면(findbyslug) 홈페이지로 리다이렉트.
}
