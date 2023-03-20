package com.demo.dto.read.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReadDto {

    Long id;

    Integer price;

    String name;

    String color;

    String description;

    ProductTypeReadDto productType;

    CategoryReadDto category;
    Set<ShareReadDto> shares;

}
