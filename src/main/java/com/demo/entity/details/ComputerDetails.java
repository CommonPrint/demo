package com.demo.entity.details;

import com.demo.entity.product.Product;
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
public class ComputerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    @OneToOne(cascade = CascadeType.ALL)
    private Product productId;

}
