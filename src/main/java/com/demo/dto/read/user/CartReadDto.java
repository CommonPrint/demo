package com.demo.dto.read.user;

import com.demo.dto.read.product.ProductReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartReadDto {

    Long id;

    UserReadDto user;

    Set<ProductReadDto> products;
}
