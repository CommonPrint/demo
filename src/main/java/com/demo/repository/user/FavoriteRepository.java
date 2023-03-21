package com.demo.repository.user;


import com.demo.entity.user.Cart;
import com.demo.entity.user.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId")
    Optional<Favorite> findByUserId(@Param("userId") Long userId);

}
