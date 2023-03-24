package com.demo.dto.read.user;

import com.demo.entity.user.Role;
import com.demo.dto.read.CityReadDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDto {
    Long id;

    String username;
    String firstname;

    String lastname;

    LocalDate birthDate;

    String clientAddress;

    String password;

    String email;

    Long phoneNumber;

    String avatar;

    CityReadDto city;

    Set<Role> role;
}
