package com.demo.dto.createedit;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CityCreateEditDto {
    private Long id;
    private String cityName;
//    private CountryReadDto countryId;
    private Long countryId;
}
