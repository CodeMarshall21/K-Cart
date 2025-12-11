package com.project.kcart.spec;

import com.project.kcart.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> hasCategory(String category){
        return (root, query, criteriaBuilder) -> category == null ? null : criteriaBuilder.equal(root.get("category"),category);
    }
}
