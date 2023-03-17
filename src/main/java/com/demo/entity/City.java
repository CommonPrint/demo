package com.demo.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "city_name", unique = true)
	private String cityName;

//    @OnDelete(action = OnDeleteAction.CASCADE) //Автоматом удалит города, если удалится город
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", columnDefinition = "null")
    private Country country;
	
}
