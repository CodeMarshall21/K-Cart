package com.project.kcart.spec;

import com.project.kcart.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> hasCategory(String category){
        return (root, query, criteriaBuilder) -> category == null ? null : criteriaBuilder.equal(root.get("category"),category);
    }

    public static Specification<Product> priceBetween(Double minPrice, Double maxPrice){
        return (root, query, criteriaBuilder) -> {
            if(minPrice == null && maxPrice == null) return null;
            if(minPrice == null) return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            if(maxPrice == null) return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
        };
    }

    public static Specification<Product> hasNameOrDescriptionLike(String keyword){
        return (root, query, cb) -> {
            if(keyword == null || keyword.isEmpty()) return null;

            return cb.or(
                    cb.like(root.get("name"), "%"+keyword.toLowerCase()+"%"),
                    cb.like(root.get("description"), "%"+keyword.toLowerCase()+"%")
            );
        };
    }
}
