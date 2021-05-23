package org.sda.springboot.controllers;


import org.sda.springboot.entities.ProductEntity;
import org.sda.springboot.repositories.CategoryRepository;
import org.sda.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/web")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public ProductController() {
        System.out.println(getClass().getSimpleName() + " created");
    }

    @GetMapping("/products")
    public ModelAndView getProducts(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/products");

        if (model.asMap().get("searchedProducts") != null) {
            modelAndView.addObject("products", model.asMap().get("searchedProducts"));
        } else {
            modelAndView.addObject("products", productRepository.findAll());
        }
        modelAndView.addObject("categories", categoryRepository.findAll());

        return modelAndView;

    }


    @GetMapping("/products/add")
    public ModelAndView addProduct() {
        ModelAndView modelAndView = new ModelAndView("admin/product-form");
        modelAndView.addObject("product", new ProductEntity());
        modelAndView.addObject("categories", categoryRepository.findAll());
        return modelAndView;

    }

    @GetMapping("/products/edit/{id}")
    public ModelAndView editProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("admin/product-form");
        modelAndView.addObject("product", productRepository.findById(id).get());
        modelAndView.addObject("categories", categoryRepository.findAll());
        return modelAndView;
    }


    @PostMapping("/products/save")
    public ModelAndView saveProduct(@ModelAttribute(name = "product") @Valid ProductEntity product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/web/products");

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("product-form");
            modelAndView.addObject("redirect:/web/products", product);
            return modelAndView;
        }
        productRepository.save(product);
        // urmatoarea linie se va folosi doar daca in constructorul modelAndView punem fisierul(products) ca o alternativa daca nu vrem sa folosim redirectul
//        modelAndView.setViewName("products");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;

    }

    @GetMapping("/products/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/web/products");
        productRepository.deleteById(id);
        return modelAndView;

    }

    @RequestMapping("/products/search")
    public ModelAndView searchProduct(@RequestParam("searchString") String productName, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/web/products");
        List<ProductEntity> productEntityList = productRepository.findAllByProductNameContaining(productName);
        modelAndView.addObject("searchedProducts", productEntityList);
        redirectAttributes.addFlashAttribute("searchedProducts",productEntityList);
        return modelAndView;
    }


}
