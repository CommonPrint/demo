package com.demo.entity.product;


import com.demo.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    @Column(length = 2000, nullable = true)
    private String dignity;

    @Column(length = 2000, nullable = true)
    private String flaws;

    @Column(length = 5000, nullable = true)
    private String textReview;

    private LocalDate dateReview;

    // Несколько отзывов к одному продукту
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;



    // У одного отзыва всего 1 пользователь
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
