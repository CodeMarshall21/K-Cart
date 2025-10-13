package com.project.kcart.seed;

import com.project.kcart.entity.Product;
import com.project.kcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if(productRepository.count() == 0){
            List<Product> demoProduct = List.of(
                    new Product(null, "iPhone 17", 1099.99, "A18 Bionic Chip smartphone with HDR display", 4.8, "Amazon", 125),
                    new Product(null, "Sony WH-1000XM6", 399.00, "Noise cancelling wireless headphones", 4.7, "Croma", 89),
                    new Product(null, "Levi’s 511 Slim Fit Jeans", 59.99, "Classic blue denim stretch jeans for men", 4.6, "Myntra", 240),
                    new Product(null, "H&M Floral Summer Dress", 39.99, "Lightweight cotton dress with floral print", 4.5, "H&M", 180),
                    new Product(null, "Philips Air Fryer HD9252", 149.99, "Rapid air technology for healthy frying", 4.7, "Amazon", 95),
                    new Product(null, "Milton Thermosteel Flask 1L", 19.99, "Double-wall insulated stainless steel flask", 4.8, "Flipkart", 310),
                    new Product(null, "Atomic Habits", 14.99, "James Clear’s guide to habit formation and success", 4.9, "Amazon", 400),
                    new Product(null, "The Psychology of Money", 12.49, "Timeless lessons on wealth and behavior by Morgan Housel", 4.8, "Amazon", 360),
                    new Product(null, "L’Oreal Revitalift Serum", 29.99, "Anti-aging face serum with hyaluronic acid", 4.6, "Nykaa", 120),
                    new Product(null, "Nivea Men Deodorant", 5.49, "Long-lasting body deodorant spray", 4.5, "BigBasket", 290),
                    new Product(null, "Yonex Astrox 99 Play", 89.99, "Badminton racket for advanced players", 4.7, "Decathlon", 55),
                    new Product(null, "Nike Air Zoom Pegasus 41", 129.99, "Running shoes with superior cushioning", 4.8, "Nike", 142),
                    new Product(null, "Tata Sampann Toor Dal 1kg", 2.49, "Unpolished protein-rich pigeon peas", 4.6, "BigBasket", 520),
                    new Product(null, "Amul Butter 500g", 3.99, "Made from pure milk cream, rich flavor", 4.9, "Flipkart", 410),
                    new Product(null, "LEGO Star Wars X-Wing", 99.99, "Collectible building set for ages 10+", 4.9, "Hamleys", 65)

            );

            productRepository.saveAll(demoProduct);
            System.out.println("DEMO PRODUCTS SEEDED SUCCESSFULLY...");
        }else{
            System.out.println("PRODUCTS ALREADY EXISTS, SKIPPING SEEDING...");
        }
    }
}
