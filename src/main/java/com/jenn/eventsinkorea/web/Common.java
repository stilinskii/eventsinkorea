package com.jenn.eventsinkorea.web;


import com.jenn.eventsinkorea.domain.admin.CategoryRepository;
import com.jenn.eventsinkorea.domain.admin.PageRepository;
import com.jenn.eventsinkorea.domain.admin.model.Category;
import com.jenn.eventsinkorea.domain.admin.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

//intercepter 적용. 다른 메서드전에 이거 호출
@ControllerAdvice
public class Common {
    @Autowired
    private PageRepository pageRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @ModelAttribute
    public void shareData(Model model, HttpSession session){
        List<Page> pages = pageRepo.findAllByOrderBySortingAsc();

    //    List<Category> categories = categoryRepo.findAll();



//        if(session.getAttribute("cart")!=null){
//            HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
//            int size = 0;
//            double total=0;
//
//            for(Cart value : cart.values()){
//                size += value.getQuantity();
//                total += value.getQuantity() * Double.parseDouble(value.getPrice());
//            }
//
//            model.addAttribute("csize",size);
//            model.addAttribute("ctotal",total);
//
//            cartActive = true;
//        }

        model.addAttribute("cpages",pages);
     //   model.addAttribute("ccategories",categories);

    }
}
