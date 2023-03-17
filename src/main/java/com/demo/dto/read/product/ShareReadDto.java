package com.demo.dto.read.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareReadDto {

    Long id;
    Integer shareValue;
    String shareType;
    Integer sharePeriod;
}
