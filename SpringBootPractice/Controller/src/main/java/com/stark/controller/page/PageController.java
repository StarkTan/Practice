package com.stark.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @RequestMapping("/index")
    public void index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
    }
}
