package com.d365lab.eatery.cartservice.repositories;

import com.d365lab.eatery.cartservice.dao.CustomerCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CustomerCartRepository extends JpaRepository<CustomerCart, Integer> {

    @Query(value = "SELECT * FROM customer_cart WHERE customer_tenant_id = :customerTenantId AND customer_cart_is_order_placed = 0", nativeQuery = true)
    CustomerCart findCartOfACustomer(@Param("customerTenantId") String customerTenantId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customer_cart WHERE customer_tenant_id = :customerTenantId AND customer_cart_is_order_placed = 0", nativeQuery = true)
    void deleteLastCartOfACustomer(@Param("customerTenantId") String customerTenantId);

    @Query(value = "SELECT * FROM customer_cart WHERE cart_id = :cartId", nativeQuery = true)
    CustomerCart findCart(@Param("cartId") Integer cartId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customer_cart SET customer_cart_is_order_placed = 1 WHERE cart_id = :cartId", nativeQuery = true)
    void updateIsOrderPlacedForCart(@Param("cartId") Integer cartId);

}
