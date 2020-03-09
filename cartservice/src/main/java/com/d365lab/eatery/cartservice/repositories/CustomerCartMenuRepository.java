package com.d365lab.eatery.cartservice.repositories;

import com.d365lab.eatery.cartservice.dao.CustomerCartMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerCartMenuRepository extends JpaRepository<CustomerCartMenu, Integer> {

    @Query(value = "SELECT * FROM customer_cart_menu WHERE cart_id = :cartId", nativeQuery = true)
    List<CustomerCartMenu> findCartMenuListForACart(@Param("cartId") Integer cartId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customer_cart_menu WHERE cart_id = :cartId", nativeQuery = true)
    void deleteCartMenuListForACart(@Param("cartId") Integer cartId);

}
