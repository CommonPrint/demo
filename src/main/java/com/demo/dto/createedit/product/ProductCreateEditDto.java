package com.demo.dto.createedit.product;

import com.demo.dto.read.product.ProductFileReadDto;
import com.demo.dto.read.product.ProductReviewReadDto;
import com.demo.dto.read.product.ShareReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateEditDto {

    private Long id;

    private Integer price;

    private String name;

    private String color;

    private String description;

    private Long productTypeId;

    private Long productGuaranteeId;

    private Long categoryId;
    private Set<ShareReadDto> shares;

    private Set<ProductFileReadDto> files;
    private Set<ProductReviewReadDto> reviews;
}
