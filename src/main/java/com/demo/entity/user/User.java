package com.demo.entity.user;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.demo.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@Table(name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email"),
				@UniqueConstraint(columnNames = "phoneNumber")
		})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

//	@NotBlank
	@Column(name = "first_name")
	private String firstname;

//	@NotBlank
	@Column(name = "last_name")
	private String lastname;


	private LocalDate birthDate;

	@Column(name = "client_address")
	private String clientAddress;

	@NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	@Email
	@Size(max = 50)
	private String email;

//	@NotBlank
	private Long phoneNumber;

	@Column(length = 1024)
	private String avatar;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", columnDefinition = "null")
	private City city;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

}
