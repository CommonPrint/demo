package com.demo.dto.createedit.order;

import com.demo.dto.read.order.InsurePropertyReadDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.order.InsureProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsureCreateEditDto {

    private Long id;

    private String typeName;

    private String description;
    private Integer price;

    private Set<InsurePropertyReadDto> properties;

}
