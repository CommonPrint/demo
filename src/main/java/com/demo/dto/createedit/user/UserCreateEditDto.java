package com.demo.dto.createedit.user;

import com.demo.entity.City;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateEditDto {
    private Long id;
    private String firstname;

    private String lastname;

    private LocalDate birthDate;

    private String clientAddress;

    private String password;

    private String email;

    private Long phoneNumber;

    private Long cityId;

}
