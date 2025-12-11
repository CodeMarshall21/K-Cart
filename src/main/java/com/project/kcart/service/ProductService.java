package com.project.kcart.service;

import com.project.kcart.entity.Product;
import com.project.kcart.repository.ProductRepository;
import com.project.kcart.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Map<String, Object> getAllProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();

        response.put("products", products.getContent());
        response.put("totalProducts", products.getTotalElements());

        return response;
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> searchProduct(String category, double minPrice, double maxPrice, String keyword, Double rating){
        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(category));

        return productRepository.findAll(spec);
    }
}
