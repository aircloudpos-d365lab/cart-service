package com.d365lab.eatery.cartservice.model;

import java.io.Serializable;

public class CartItem implements Serializable {

    private Integer cartMenuId;
    private Integer cartId;
    private Integer cartRestaurantMenuId;
    private String cartRestaurantTenantId;
    private String cartRestaurantMenuName;
    private String cartRestaurantMenuDescription;
    private String cartRestaurantMenuType;
    private String cartRestaurantMenuCategory;
    private String cartRestaurantMenuPhoto;
    private Double cartRestaurantMenuPrice;
    private Double cartRestaurantMenuPriceCgstPercentage;
    private Double cartRestaurantMenuPriceSgstPercentage;
    private Double cartRestaurantMenuRating;
    private Double cartRestaurantMenuFinalPrice;
    private Integer cartRestaurantMenuQty;

    public CartItem() {
    }

    public CartItem(Integer cartMenuId, Integer cartId, Integer cartRestaurantMenuId, String cartRestaurantTenantId, String cartRestaurantMenuName, String cartRestaurantMenuDescription, String cartRestaurantMenuType, String cartRestaurantMenuCategory, String cartRestaurantMenuPhoto, Double cartRestaurantMenuPrice, Double cartRestaurantMenuPriceCgstPercentage, Double cartRestaurantMenuPriceSgstPercentage, Double cartRestaurantMenuRating, Double cartRestaurantMenuFinalPrice, Integer cartRestaurantMenuQty) {
        this.cartMenuId = cartMenuId;
        this.cartId = cartId;
        this.cartRestaurantMenuId = cartRestaurantMenuId;
        this.cartRestaurantTenantId = cartRestaurantTenantId;
        this.cartRestaurantMenuName = cartRestaurantMenuName;
        this.cartRestaurantMenuDescription = cartRestaurantMenuDescription;
        this.cartRestaurantMenuType = cartRestaurantMenuType;
        this.cartRestaurantMenuCategory = cartRestaurantMenuCategory;
        this.cartRestaurantMenuPhoto = cartRestaurantMenuPhoto;
        this.cartRestaurantMenuPrice = cartRestaurantMenuPrice;
        this.cartRestaurantMenuPriceCgstPercentage = cartRestaurantMenuPriceCgstPercentage;
        this.cartRestaurantMenuPriceSgstPercentage = cartRestaurantMenuPriceSgstPercentage;
        this.cartRestaurantMenuRating = cartRestaurantMenuRating;
        this.cartRestaurantMenuFinalPrice = cartRestaurantMenuFinalPrice;
        this.cartRestaurantMenuQty = cartRestaurantMenuQty;
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

    public String getCartRestaurantTenantId() {
        return cartRestaurantTenantId;
    }

    public void setCartRestaurantTenantId(String cartRestaurantTenantId) {
        this.cartRestaurantTenantId = cartRestaurantTenantId;
    }

    public String getCartRestaurantMenuName() {
        return cartRestaurantMenuName;
    }

    public void setCartRestaurantMenuName(String cartRestaurantMenuName) {
        this.cartRestaurantMenuName = cartRestaurantMenuName;
    }

    public String getCartRestaurantMenuDescription() {
        return cartRestaurantMenuDescription;
    }

    public void setCartRestaurantMenuDescription(String cartRestaurantMenuDescription) {
        this.cartRestaurantMenuDescription = cartRestaurantMenuDescription;
    }

    public String getCartRestaurantMenuType() {
        return cartRestaurantMenuType;
    }

    public void setCartRestaurantMenuType(String cartRestaurantMenuType) {
        this.cartRestaurantMenuType = cartRestaurantMenuType;
    }

    public String getCartRestaurantMenuCategory() {
        return cartRestaurantMenuCategory;
    }

    public void setCartRestaurantMenuCategory(String cartRestaurantMenuCategory) {
        this.cartRestaurantMenuCategory = cartRestaurantMenuCategory;
    }

    public String getCartRestaurantMenuPhoto() {
        return cartRestaurantMenuPhoto;
    }

    public void setCartRestaurantMenuPhoto(String cartRestaurantMenuPhoto) {
        this.cartRestaurantMenuPhoto = cartRestaurantMenuPhoto;
    }

    public Double getCartRestaurantMenuPrice() {
        return cartRestaurantMenuPrice;
    }

    public void setCartRestaurantMenuPrice(Double cartRestaurantMenuPrice) {
        this.cartRestaurantMenuPrice = cartRestaurantMenuPrice;
    }

    public Double getCartRestaurantMenuPriceCgstPercentage() {
        return cartRestaurantMenuPriceCgstPercentage;
    }

    public void setCartRestaurantMenuPriceCgstPercentage(Double cartRestaurantMenuPriceCgstPercentage) {
        this.cartRestaurantMenuPriceCgstPercentage = cartRestaurantMenuPriceCgstPercentage;
    }

    public Double getCartRestaurantMenuPriceSgstPercentage() {
        return cartRestaurantMenuPriceSgstPercentage;
    }

    public void setCartRestaurantMenuPriceSgstPercentage(Double cartRestaurantMenuPriceSgstPercentage) {
        this.cartRestaurantMenuPriceSgstPercentage = cartRestaurantMenuPriceSgstPercentage;
    }

    public Double getCartRestaurantMenuRating() {
        return cartRestaurantMenuRating;
    }

    public void setCartRestaurantMenuRating(Double cartRestaurantMenuRating) {
        this.cartRestaurantMenuRating = cartRestaurantMenuRating;
    }

    public Double getCartRestaurantMenuFinalPrice() {
        return cartRestaurantMenuFinalPrice;
    }

    public void setCartRestaurantMenuFinalPrice(Double cartRestaurantMenuFinalPrice) {
        this.cartRestaurantMenuFinalPrice = cartRestaurantMenuFinalPrice;
    }

    public Integer getCartRestaurantMenuQty() {
        return cartRestaurantMenuQty;
    }

    public void setCartRestaurantMenuQty(Integer cartRestaurantMenuQty) {
        this.cartRestaurantMenuQty = cartRestaurantMenuQty;
    }
}
