package com.project.kcart.controller;

import com.project.kcart.entity.Product;
import com.project.kcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Map<String, Object> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size){
        return productService.getAllProducts(page, size);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam(required = false) String category, @RequestParam(required = false) Double minPrice,
                                       @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String keyword,
                                       @RequestParam(required = false) Double rating){
        return productService.searchProduct(category, minPrice, maxPrice, keyword, rating);
    }

    

}


