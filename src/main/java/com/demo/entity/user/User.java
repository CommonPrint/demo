package com.demo.entity.user;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.demo.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstname;

	@Column(name = "last_name")
	private String lastname;

	@Column(nullable = true)
	private LocalDate birthDate;

	@Column(name = "client_address")
	private String clientAddress;

	private String password;

	@Column(unique = true)
	private String email;

	private Long phoneNumber;

	@Column(length = 1024)
	private String avatar;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", columnDefinition = "null")
	private City city;

}
