package com.demo.entity.product;


import com.demo.entity.City;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    private String name;

    private String color;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_guarantee_id")
    private ProductGuarantee productGuarantee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", columnDefinition = "null")
    private Category category;


    // У одного продукта может быть несколько акций и скидок
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_shares",
                joinColumns = @JoinColumn(name = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "share_id"))
    private Set<Share> shares = new HashSet<>();


//    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_files",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_file_id"))
    private Set<ProductFile> files = new HashSet<>();

    // У одного продукта может быть несколько акций и скидок
//    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_reviews",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_review_id"))
    private Set<ProductReview> reviews = new HashSet<>();
}
