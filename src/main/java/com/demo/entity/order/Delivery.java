package com.demo.entity.order;


import com.demo.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String deliveryType;

    private LocalDate deliveryDate;

    @Column(nullable = false)
    private String address;

    @Column(length = 1000, nullable = true)
    private String comment;

}
