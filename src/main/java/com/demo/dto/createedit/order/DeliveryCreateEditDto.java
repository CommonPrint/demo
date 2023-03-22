package com.demo.dto.createedit.order;

import com.demo.dto.read.order.InsurePropertyReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryCreateEditDto {

    private Long id;

    private String deliveryType;

    private LocalDate deliveryDate;
    private String address;

    private String comment;

}
