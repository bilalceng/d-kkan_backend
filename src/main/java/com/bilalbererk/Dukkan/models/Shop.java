package com.bilalbererk.Dukkan.models;

import com.bilalbererk.Dukkan.utils.ShopCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "_shop")
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_seq_gen")
    @SequenceGenerator(name = "shop_seq_gen", sequenceName = "shop_id_gen", allocationSize = 1)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    ShopCategory shopCategory;

    @Column(name = "opening_hours")
    private String openingHours;

    @Column(name = "website")
    private String website;

    @Column(name = "rating")
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "sold_product_number")
    int soldProductNumber;
    @JsonManagedReference
    @OneToMany(mappedBy = "shop", orphanRemoval = true, cascade = CascadeType.ALL)
    Set<Product> products;

    @Column(name = "message_number")
    int  messageNumber;

}
