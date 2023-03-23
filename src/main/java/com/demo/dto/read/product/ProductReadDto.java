package com.demo.dto.read.product;

import com.demo.entity.product.ProductFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
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

    Set<ProductFileReadDto> files;
}
