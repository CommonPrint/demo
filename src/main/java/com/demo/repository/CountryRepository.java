package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
	
	@Query("SELECT c FROM Country c WHERE c.id = :countryId")
	Optional<Country> findById(@Param("countryId") Long id);

}
