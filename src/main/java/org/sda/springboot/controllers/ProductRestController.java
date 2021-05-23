package org.sda.springboot.controllers;


import org.sda.springboot.entities.ProductEntity;
import org.sda.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    public ProductRestController() {
        System.out.println(getClass().getSimpleName() + " created");
    }

    @GetMapping("/products")   // primul rest api care returneaza din baza de date
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/products/save")
    public String saveProduct(@RequestBody ProductEntity productEntity) { // aici trecem ce vrem sa salvam in body
        productRepository.save(productEntity);
        return "Succes";
    }

    @GetMapping("/products/{id}")
    // numele variabilei trebuie sa fie exact acelasi cu numele variabilei ca parametru de la metoda
    public ProductEntity getProductById(@PathVariable(name = "id") Integer productId) {  // name="id" indica la ce nume sa mapeze metoda, acesta trebuie sa coincida cu parametrul de la get
        return productRepository.findById(productId).get();

    }

    @GetMapping("/productById")
    public ProductEntity getProductByIdParam(@RequestParam(name = "id", defaultValue = "1") Integer productId) {
//        return productRepository.findById(productId).get();

        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        if (productEntityOptional.isPresent()) {
            return productEntityOptional.get();
        } else return new ProductEntity();
    }

    @GetMapping("/productByIdNew")
    public ResponseEntity<ProductEntity> getProductByIdParamNew(@RequestParam(name = "id", defaultValue = "5") Integer productId) {
//        return productRepository.findById(productId).get();

        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        if (productEntityOptional.isPresent()) {
            return ResponseEntity.ok().body(productEntityOptional.get());
        } else {
            return ResponseEntity.status(404).header("X-Error","Id not found").body(null);
        }
    }
}
