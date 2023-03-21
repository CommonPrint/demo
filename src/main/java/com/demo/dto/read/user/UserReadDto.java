package com.demo.dto.read.user;

import com.demo.dto.read.CityReadDto;
import com.demo.entity.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDto {
    Long id;
    String firstname;

    String lastname;

    LocalDate birthDate;

    String clientAddress;

    String password;

    String email;

    Long phoneNumber;

    CityReadDto city;
}
