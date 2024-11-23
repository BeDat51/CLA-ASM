package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.ToString.Exclude;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude // Exclude category to avoid StackOverflow
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @ToString.Exclude // Exclude brand to avoid StackOverflow
    private Brand brand;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude // Exclude cartItems to avoid StackOverflow
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude // Exclude orderItems to avoid StackOverflow
    private List<OrderItem> orderItems;

    @ToString.Include(name = "reviews")
    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }
}
