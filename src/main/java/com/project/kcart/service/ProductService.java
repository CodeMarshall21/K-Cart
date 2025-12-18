package com.project.kcart.service;

import com.project.kcart.dto.ProductDto;
import com.project.kcart.dto.ProductImageDto;
import com.project.kcart.dto.ProductReviewDto;
import com.project.kcart.entity.Product;
import com.project.kcart.entity.ProductReview;
import com.project.kcart.repository.ProductRepository;
import com.project.kcart.repository.ProductReviewRepository;
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
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public Map<String, Object> getAllProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductDto> productDtos = products.stream().map(this::convertToDto).toList();
        Map<String, Object> response = new HashMap<>();

        response.put("products", productDtos);
        response.put("totalProducts", products.getTotalElements());

        return response;
    }

    public ProductDto convertToDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setRating(product.getRating());
        productDto.setSeller(product.getSeller());
        productDto.setStock(product.getStock());
        productDto.setNumOfReviews(product.getNumOfReviews());

        List<ProductReviewDto> reviewDtos = product.getReviews().stream().map( review -> {
            ProductReviewDto reviewDto = new ProductReviewDto();
            reviewDto.setId(review.getId());
            reviewDto.setProductId(review.getProduct().getId());
            reviewDto.setComment(review.getComment());
            reviewDto.setRating(review.getRating());
            return reviewDto;
        }).toList();

        productDto.setReviews(reviewDtos);

        List<ProductImageDto> imageDtos = product.getImages().stream().map( image -> {
            ProductImageDto imageDto = new ProductImageDto();
            imageDto.setPublicId(image.getPublicId());
            return imageDto;
        }).toList();

        productDto.setImages(imageDtos);

        return productDto;
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> searchProduct(String category, Double minPrice, Double maxPrice, String keyword, Double rating){
        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(category))
                                                    .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                                                    .and(ProductSpecification.hasNameOrDescriptionLike(keyword))
                                                    .and(ProductSpecification.ratingsGreaterThan(rating))
                ;

        return productRepository.findAll(spec);
    }

    public void addReview(ProductReviewDto reviewDto){
        Product product = productRepository.findById(reviewDto.getProductId()).orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND !"));

        ProductReview productReview = new ProductReview();

        productReview.setComment(reviewDto.getComment());
        productReview.setRating(reviewDto.getRating());
        productReview.setProduct(product);

        productReviewRepository.save(productReview);
    }
}
