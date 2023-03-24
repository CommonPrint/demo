package com.demo.dto.read.product;


import com.demo.dto.read.user.UserReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewReadDto {

    Long id;

    Integer rating;

    String dignity;

    String flaws;

    String textReview;

    LocalDate dateReview;

//    ProductReadDto product;

    UserReadDto user;

}
