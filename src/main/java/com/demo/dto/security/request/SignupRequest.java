package com.demo.dto.security.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
public class SignupRequest {

    private String firstname;
    private String lastname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    private LocalDate birthDate;

    private String clientAddress;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Long phoneNumber;

    private String avatar;
    private Long cityId;

}
