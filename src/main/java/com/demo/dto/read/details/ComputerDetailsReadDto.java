package com.demo.dto.read.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputerDetailsReadDto {

    Long id;
    String model;
    Long productId;
}
