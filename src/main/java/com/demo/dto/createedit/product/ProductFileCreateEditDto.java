package com.demo.dto.createedit.product;

import com.demo.dto.read.product.ShareReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFileCreateEditDto {

    private Long id;

    private String url;

    private boolean avatar;
}
