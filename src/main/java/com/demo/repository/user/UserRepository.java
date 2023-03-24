package com.demo.repository.user;

import com.demo.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.city.id = :cityId")
    List<User> findAllByCityId(@Param("cityId") Long cityId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.avatar = :avatar WHERE u.id = :userId")
    void setUserAvatar(@Param("avatar") String avatar, @Param("userId") Long userId);

}
