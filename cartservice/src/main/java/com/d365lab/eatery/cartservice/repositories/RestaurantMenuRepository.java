package com.d365lab.eatery.cartservice.repositories;


import com.d365lab.eatery.cartservice.dao.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu, Long> {

    @Query(value = "SELECT * FROM restaurant_menu WHERE restaurant_menu_id = :restaurantMenuId", nativeQuery = true)
    RestaurantMenu findMenuDetailsForMenu(@Param("restaurantMenuId") Integer restaurantMenuId);

}
