package org.sda.springboot.controllers;

import org.sda.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontPageController {
    @Autowired
    private ProductRepository productRepository;

    public FrontPageController() {
        System.out.println(getClass().getSimpleName() + " created");

    }

    @GetMapping("/frontpage1")  // URL-ul in browser va fi localhost:8080/frontpage
    public ModelAndView getFrontPage() {
        ModelAndView modelAndView = new ModelAndView("frontpage");
        modelAndView.addObject("myName", "Name");

        return modelAndView;
    }


}
