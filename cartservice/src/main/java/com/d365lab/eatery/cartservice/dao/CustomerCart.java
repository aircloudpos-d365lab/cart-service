package com.d365lab.eatery.cartservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "customer_cart")
@Table(name = "customer_cart")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class CustomerCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer cartId;

    @NotNull
    @Size(max = 30)
    @Column(name = "restaurant_tenant_id", unique = true)
    private String restaurantTenantId;

    @NotNull
    @Size(max = 30)
    @Column(name = "customer_tenant_id", unique = true)
    private String customerTenantId;

    @Column(name="customer_cart_is_order_placed")
    private Integer customerCartIsOrderPlaced;

    @Column(name = "customer_cart_coupon_applied")
    private String customerCartCouponApplied;

    @Column(name = "cart_total")
    private Double cartTotal;

    @Column(name = "cart_delivery_fee")
    private Double cartDeliveryFee;

    @Column(name = "cart_discount_total")
    private Double cartDiscountTotal;

    @Column(name = "cart_grand_total")
    private Double cartGrandTotal;

    @Column(name = "cart_item_price_total")
    private Double cartItemPriceTotal;

    @Column(name = "cart_total_cgst_percentage")
    private Double cartTotalCgstPercentage;

    @Column(name = "cart_total_sgst_percentage")
    private Double cartTotalSgstPercentage;

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

    public CustomerCart() {
    }

    public CustomerCart(Integer cartId, @NotNull @Size(max = 30) String restaurantTenantId, @NotNull @Size(max = 30) String customerTenantId, Integer customerCartIsOrderPlaced, String customerCartCouponApplied, Double cartTotal, Double cartDeliveryFee, Double cartDiscountTotal, Double cartGrandTotal, Double cartItemPriceTotal, Double cartTotalCgstPercentage, Double cartTotalSgstPercentage) {
        this.cartId = cartId;
        this.restaurantTenantId = restaurantTenantId;
        this.customerTenantId = customerTenantId;
        this.customerCartIsOrderPlaced = customerCartIsOrderPlaced;
        this.customerCartCouponApplied = customerCartCouponApplied;
        this.cartTotal = cartTotal;
        this.cartDeliveryFee = cartDeliveryFee;
        this.cartDiscountTotal = cartDiscountTotal;
        this.cartGrandTotal = cartGrandTotal;
        this.cartItemPriceTotal = cartItemPriceTotal;
        this.cartTotalCgstPercentage = cartTotalCgstPercentage;
        this.cartTotalSgstPercentage = cartTotalSgstPercentage;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getRestaurantTenantId() {
        return restaurantTenantId;
    }

    public void setRestaurantTenantId(String restaurantTenantId) {
        this.restaurantTenantId = restaurantTenantId;
    }

    public String getCustomerTenantId() {
        return customerTenantId;
    }

    public void setCustomerTenantId(String customerTenantId) {
        this.customerTenantId = customerTenantId;
    }

    public Integer getCustomerCartIsOrderPlaced() {
        return customerCartIsOrderPlaced;
    }

    public void setCustomerCartIsOrderPlaced(Integer customerCartIsOrderPlaced) {
        this.customerCartIsOrderPlaced = customerCartIsOrderPlaced;
    }

    public String getCustomerCartCouponApplied() {
        return customerCartCouponApplied;
    }

    public void setCustomerCartCouponApplied(String customerCartCouponApplied) {
        this.customerCartCouponApplied = customerCartCouponApplied;
    }

    public Double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Double cartTotal) {
        this.cartTotal = cartTotal;
    }

    public Double getCartDeliveryFee() {
        return cartDeliveryFee;
    }

    public void setCartDeliveryFee(Double cartDeliveryFee) {
        this.cartDeliveryFee = cartDeliveryFee;
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

    public Double getCartDiscountTotal() {
        return cartDiscountTotal;
    }

    public void setCartDiscountTotal(Double cartDiscountTotal) {
        this.cartDiscountTotal = cartDiscountTotal;
    }

    public Double getCartGrandTotal() {
        return cartGrandTotal;
    }

    public void setCartGrandTotal(Double cartGrandTotal) {
        this.cartGrandTotal = cartGrandTotal;
    }

    public Double getCartItemPriceTotal() {
        return cartItemPriceTotal;
    }

    public void setCartItemPriceTotal(Double cartItemPriceTotal) {
        this.cartItemPriceTotal = cartItemPriceTotal;
    }

    public Double getCartTotalCgstPercentage() {
        return cartTotalCgstPercentage;
    }

    public void setCartTotalCgstPercentage(Double cartTotalCgstPercentage) {
        this.cartTotalCgstPercentage = cartTotalCgstPercentage;
    }

    public Double getCartTotalSgstPercentage() {
        return cartTotalSgstPercentage;
    }

    public void setCartTotalSgstPercentage(Double cartTotalSgstPercentage) {
        this.cartTotalSgstPercentage = cartTotalSgstPercentage;
    }
}
