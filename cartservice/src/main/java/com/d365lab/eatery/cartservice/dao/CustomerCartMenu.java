package com.d365lab.eatery.cartservice.dao;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "customer_cart_menu")
@Table(name = "customer_cart_menu")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class CustomerCartMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_menu_id")
    private Integer cartMenuId;

    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "cart_restaurant_menu_id")
    private Integer cartRestaurantMenuId;

    @Column(name = "cart_restaurant_menu_qty")
    private Integer cartRestaurantMenuQty;

    @Column(nullable = false, updatable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @NotNull
    private Date createdAt;

    @Column(nullable = false, name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @NotNull
    private Date updatedAt;

    public CustomerCartMenu() {
    }

    public CustomerCartMenu(Integer cartMenuId, Integer cartId, Integer cartRestaurantMenuId, Integer cartRestaurantMenuQty, @NotNull Date createdAt, @NotNull Date updatedAt) {
        this.cartMenuId = cartMenuId;
        this.cartId = cartId;
        this.cartRestaurantMenuId = cartRestaurantMenuId;
        this.cartRestaurantMenuQty = cartRestaurantMenuQty;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getCartMenuId() {
        return cartMenuId;
    }

    public void setCartMenuId(Integer cartMenuId) {
        this.cartMenuId = cartMenuId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartRestaurantMenuId() {
        return cartRestaurantMenuId;
    }

    public void setCartRestaurantMenuId(Integer cartRestaurantMenuId) {
        this.cartRestaurantMenuId = cartRestaurantMenuId;
    }

    public Integer getCartRestaurantMenuQty() {
        return cartRestaurantMenuQty;
    }

    public void setCartRestaurantMenuQty(Integer cartRestaurantMenuQty) {
        this.cartRestaurantMenuQty = cartRestaurantMenuQty;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
