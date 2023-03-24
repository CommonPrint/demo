package com.demo.repository.product;

import com.demo.entity.product.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

//    @Query("SELECT r FROM ProductReview r WHERE r.product.id = :productId")
//    List<ProductReview> findAllByProductId(@Param("productId") Long productId);

}
