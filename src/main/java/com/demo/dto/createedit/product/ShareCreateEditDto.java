package com.demo.dto.createedit.product;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ShareCreateEditDto {

    private Long id;
    private Integer shareValue;
    private String shareType;
    private Integer sharePeriod;

}
