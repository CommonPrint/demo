package com.demo.entity.product;

import com.demo.entity.City;
import com.demo.entity.Country;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 768)
    private String url;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", columnDefinition = "null")
//    private Product product;

}
