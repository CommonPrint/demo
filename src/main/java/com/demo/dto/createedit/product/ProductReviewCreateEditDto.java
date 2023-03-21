package com.demo.dto.createedit.product;

import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewCreateEditDto {

    private Long id;

    private Integer rating;

    private String dignity;

    private String flaws;

    private String textReview;

    private LocalDate dateReview;

    private Long productId;

    private Long userId;

}
