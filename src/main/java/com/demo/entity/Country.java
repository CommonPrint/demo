package com.demo.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "country_name", unique = true)
	private String countryName;

	@Builder.Default
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<City> cities = new ArrayList<>();
}
