package com.project.kcart.controller;

import com.project.kcart.entity.Product;
import com.project.kcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Map<String, Object> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return productService.getAllProducts(page, size);
    }

}
