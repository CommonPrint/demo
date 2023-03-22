package com.demo.dto.read.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsureReadDto {

    Long id;

    String typeName;

    String description;

    Integer price;
    Set<InsurePropertyReadDto> properties;

}
