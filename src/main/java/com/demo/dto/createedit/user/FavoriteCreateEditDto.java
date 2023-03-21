package com.demo.dto.createedit.user;

import com.demo.dto.read.product.ProductReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteCreateEditDto {

    private Long id;
    private Long userId;
    private Set<ProductReadDto> products;

}
