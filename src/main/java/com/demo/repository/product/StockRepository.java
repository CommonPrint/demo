package com.demo.repository.product;

import com.demo.entity.Country;
import com.demo.entity.product.Product;
import com.demo.entity.product.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT s FROM Stock s WHERE s.id = :productId")
    Optional<Stock> findByProductId(@Param("productId") Long id);
}
