package org.sda.springboot.controllers;

import org.sda.springboot.entities.CategoryEntity;
import org.sda.springboot.entities.ProductEntity;
import org.sda.springboot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/web")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories/add")
    public ModelAndView addCategory() {
        ModelAndView modelAndView = new ModelAndView("admin/category.form");
        modelAndView.addObject("category", new CategoryEntity());
        return modelAndView;

    }

    @PostMapping("/categories/save")
    public ModelAndView saveCategory(@ModelAttribute(name = "category") @Valid CategoryEntity category, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/web/products");

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/category.form");
            modelAndView.addObject("category", category);
            return modelAndView;
        }
        categoryRepository.save(category);
        // urmatoarea linie se va folosi doar daca in constructorul modelAndView punem fisierul(products) ca o alternativa daca nu vrem sa folosim redirectul
//        modelAndView.setViewName("products");
        return modelAndView;

    }
    @GetMapping("/categories/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/web/products");
        categoryRepository.deleteById(id);
        return modelAndView;

    }
    @GetMapping("/categories/edit/{id}")
    public ModelAndView editProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("category.form");
        modelAndView.addObject("category", categoryRepository.findById(id).get());

        return modelAndView;
    }



}
