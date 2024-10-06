package com.bilalbererk.Dukkan.models;

import com.bilalbererk.Dukkan.utils.ProductCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "_product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "product_id_gen", allocationSize = 1)
    private Integer id;

    @Column(name = "product_category")
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "product_type")
    private String type;

    @Column(name = "sold_quantity")
    private int soldQuantity;
    @Column(name= "discount")
    private double discount;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "point")
    double point;

    @Column(name = "brand")
    String brand;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    Set<Message> messages;

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    List<OrderItem> orderItems;

}

