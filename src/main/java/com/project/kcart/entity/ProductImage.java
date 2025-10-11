package com.project.kcart.entity;


import jakarta.persistence.*;

@Entity
//@Table(name = "")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicId;
    private String url;
}
