package com.d365lab.eatery.cartservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "restaurant_menu")
@Table(name = "restaurant_menu")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class RestaurantMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_menu_id")
    private Integer restaurantMenuId;

    @Size(max = 30)
    @Column(name = "restaurant_outlet_tenant_id")
    private String restaurantTenantId;

    @Size(max = 50)
    @Column(name = "restaurant_menu_name")
    private String restaurantMenuName;

    @Column(name = "restaurant_menu_description", columnDefinition="LONGTEXT")
    private String restaurantMenuDescription;

    @Column(name = "restaurant_menu_type", columnDefinition="TEXT")
    private String restaurantMenuType;

    @Column(name = "restaurant_menu_category")
    private String restaurantMenuCategory;

    @Column(name = "restaurant_menu_photo_url")
    private String restaurantMenuPhoto;

    @Column(name = "restaurant_menu_price")
    private Double restaurantMenuPrice;

    @Column(name = "restaurant_menu_price_cgst_percentage")
    private Double restaurantMenuPriceCgstPercentage;

    @Column(name = "restaurant_menu_price_sgst_percentage")
    private Double restaurantMenuPriceSgstPercentage;

    @Column(name = "restaurant_menu_rating")
    private Double restaurantMenuRating;

    @Column(name = "restaurant_menu_final_price")
    private Double restaurantMenuFinalPrice;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public RestaurantMenu() {
    }

    public RestaurantMenu(Integer restaurantMenuId, @Size(max = 30) String restaurantTenantId, @Size(max = 50) String restaurantMenuName, String restaurantMenuDescription, String restaurantMenuType, String restaurantMenuCategory, String restaurantMenuPhoto, Double restaurantMenuPrice, Double restaurantMenuPriceCgstPercentage, Double restaurantMenuPriceSgstPercentage, Double restaurantMenuRating) {
        this.restaurantMenuId = restaurantMenuId;
        this.restaurantTenantId = restaurantTenantId;
        this.restaurantMenuName = restaurantMenuName;
        this.restaurantMenuDescription = restaurantMenuDescription;
        this.restaurantMenuType = restaurantMenuType;
        this.restaurantMenuCategory = restaurantMenuCategory;
        this.restaurantMenuPhoto = restaurantMenuPhoto;
        this.restaurantMenuPrice = restaurantMenuPrice;
        this.restaurantMenuPriceCgstPercentage = restaurantMenuPriceCgstPercentage;
        this.restaurantMenuPriceSgstPercentage = restaurantMenuPriceSgstPercentage;
        this.restaurantMenuRating = restaurantMenuRating;
    }

    public RestaurantMenu(Integer restaurantMenuId, @Size(max = 30) String restaurantTenantId, @Size(max = 50) String restaurantMenuName, String restaurantMenuDescription, String restaurantMenuType, String restaurantMenuCategory, String restaurantMenuPhoto, Double restaurantMenuPrice, Double restaurantMenuPriceCgstPercentage, Double restaurantMenuPriceSgstPercentage, Double restaurantMenuRating, Double restaurantMenuFinalPrice, Date createdAt, Date updatedAt) {
        this.restaurantMenuId = restaurantMenuId;
        this.restaurantTenantId = restaurantTenantId;
        this.restaurantMenuName = restaurantMenuName;
        this.restaurantMenuDescription = restaurantMenuDescription;
        this.restaurantMenuType = restaurantMenuType;
        this.restaurantMenuCategory = restaurantMenuCategory;
        this.restaurantMenuPhoto = restaurantMenuPhoto;
        this.restaurantMenuPrice = restaurantMenuPrice;
        this.restaurantMenuPriceCgstPercentage = restaurantMenuPriceCgstPercentage;
        this.restaurantMenuPriceSgstPercentage = restaurantMenuPriceSgstPercentage;
        this.restaurantMenuRating = restaurantMenuRating;
        this.restaurantMenuFinalPrice = restaurantMenuFinalPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RestaurantMenu(@Size(max = 30) String restaurantTenantId, @Size(max = 50) String restaurantMenuName, String restaurantMenuDescription, String restaurantMenuType, String restaurantMenuCategory, String restaurantMenuPhoto, Double restaurantMenuPrice, Double restaurantMenuPriceCgstPercentage, Double restaurantMenuPriceSgstPercentage, Double restaurantMenuRating, Double restaurantMenuFinalPrice) {
        this.restaurantTenantId = restaurantTenantId;
        this.restaurantMenuName = restaurantMenuName;
        this.restaurantMenuDescription = restaurantMenuDescription;
        this.restaurantMenuType = restaurantMenuType;
        this.restaurantMenuCategory = restaurantMenuCategory;
        this.restaurantMenuPhoto = restaurantMenuPhoto;
        this.restaurantMenuPrice = restaurantMenuPrice;
        this.restaurantMenuPriceCgstPercentage = restaurantMenuPriceCgstPercentage;
        this.restaurantMenuPriceSgstPercentage = restaurantMenuPriceSgstPercentage;
    }

    public Integer getRestaurantMenuId() {
        return restaurantMenuId;
    }

    public void setRestaurantMenuId(Integer restaurantMenuId) {
        this.restaurantMenuId = restaurantMenuId;
    }

    public String getRestaurantTenantId() {
        return restaurantTenantId;
    }

    public void setRestaurantTenantId(String restaurantTenantId) {
        this.restaurantTenantId = restaurantTenantId;
    }

    public String getRestaurantMenuName() {
        return restaurantMenuName;
    }

    public void setRestaurantMenuName(String restaurantMenuName) {
        this.restaurantMenuName = restaurantMenuName;
    }

    public String getRestaurantMenuDescription() {
        return restaurantMenuDescription;
    }

    public void setRestaurantMenuDescription(String restaurantMenuDescription) {
        this.restaurantMenuDescription = restaurantMenuDescription;
    }

    public String getRestaurantMenuType() {
        return restaurantMenuType;
    }

    public void setRestaurantMenuType(String restaurantMenuType) {
        this.restaurantMenuType = restaurantMenuType;
    }

    public String getRestaurantMenuPhoto() {
        return restaurantMenuPhoto;
    }

    public Double getRestaurantMenuPrice() {
        return restaurantMenuPrice;
    }

    public void setRestaurantMenuPrice(Double restaurantMenuPrice) {
        this.restaurantMenuPrice = restaurantMenuPrice;
    }

    public Double getRestaurantMenuRating() {
        return restaurantMenuRating;
    }

    public void setRestaurantMenuRating(Double restaurantMenuRating) {
        this.restaurantMenuRating = restaurantMenuRating;
    }

    public Double getRestaurantMenuPriceCgstPercentage() {
        return restaurantMenuPriceCgstPercentage;
    }

    public void setRestaurantMenuPriceCgstPercentage(Double restaurantMenuPriceCgstPercentage) {
        this.restaurantMenuPriceCgstPercentage = restaurantMenuPriceCgstPercentage;
    }

    public Double getRestaurantMenuPriceSgstPercentage() {
        return restaurantMenuPriceSgstPercentage;
    }

    public void setRestaurantMenuPriceSgstPercentage(Double restaurantMenuPriceSgstPercentage) {
        this.restaurantMenuPriceSgstPercentage = restaurantMenuPriceSgstPercentage;
    }

    public String getRestaurantMenuCategory() {
        return restaurantMenuCategory;
    }

    public void setRestaurantMenuCategory(String restaurantMenuCategory) {
        this.restaurantMenuCategory = restaurantMenuCategory;
    }

    public void setRestaurantMenuPhoto(String restaurantMenuPhoto) {
        this.restaurantMenuPhoto = restaurantMenuPhoto;
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

    public Double getRestaurantMenuFinalPrice() {
        return restaurantMenuFinalPrice;
    }

    public void setRestaurantMenuFinalPrice(Double restaurantMenuFinalPrice) {
        this.restaurantMenuFinalPrice = restaurantMenuFinalPrice;
    }
}
