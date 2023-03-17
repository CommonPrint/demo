package com.demo.dto.createedit.product;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateEditDto {

    private Long id;
    private String categoryName;

}
