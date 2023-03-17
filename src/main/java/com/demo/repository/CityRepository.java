package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT c FROM City c WHERE c.country.id = :countryId")
    List<City> findAllByCountryId(@Param("countryId") Long countryId);
}
