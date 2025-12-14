package com.project.kcart.controller;

import com.project.kcart.dto.ProductReviewDto;
import com.project.kcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/reviews")
public class ProductReviewController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ProductReviewDto productReviewDto){
        productService.addReview(productReviewDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Product Review Added");
    }
}
