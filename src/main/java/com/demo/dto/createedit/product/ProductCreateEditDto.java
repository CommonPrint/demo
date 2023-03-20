package com.demo.dto.createedit.product;

import com.demo.dto.read.product.CategoryReadDto;
import com.demo.dto.read.product.ProductTypeReadDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.product.Share;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    private Long categoryId;
    private Set<ShareReadDto> shares;

}
