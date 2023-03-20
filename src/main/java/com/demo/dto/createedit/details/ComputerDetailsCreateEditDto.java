package com.demo.dto.createedit.details;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ComputerDetailsCreateEditDto {

    private Long id;
    private String model;
    private Long productId;

}
