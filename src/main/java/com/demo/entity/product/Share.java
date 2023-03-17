package com.demo.entity.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer shareValue; // Размер скидки, рассрочки

    @Column(nullable = false, unique = true)
    private String shareType; // рассрочка, скидка

    @Column(nullable = false)
    private Integer sharePeriod;
}
