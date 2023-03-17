package com.demo.dto.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Value
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityReadDto {

    Long id;
    String cityName;
    CountryReadDto country;

}
