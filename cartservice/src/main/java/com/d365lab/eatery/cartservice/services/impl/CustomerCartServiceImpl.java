package com.d365lab.eatery.cartservice.services.impl;

import com.d365lab.eatery.cartservice.dao.CustomerCart;
import com.d365lab.eatery.cartservice.dao.RestaurantMenu;
import com.d365lab.eatery.cartservice.model.Cart;
import com.d365lab.eatery.cartservice.dao.CustomerCartMenu;
import com.d365lab.eatery.cartservice.model.CartItem;
import com.d365lab.eatery.cartservice.repositories.CustomerCartMenuRepository;
import com.d365lab.eatery.cartservice.repositories.CustomerCartRepository;
import com.d365lab.eatery.cartservice.repositories.RestaurantMenuRepository;
import com.d365lab.eatery.cartservice.services.CustomerCartService;
import com.d365lab.eatery.cartservice.utils.exceptions.CartServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.d365lab.eatery.cartservice.utils.CartServiceConstants.*;

@Service
public class CustomerCartServiceImpl implements CustomerCartService {

    @Autowired
    private CustomerCartRepository customerCartRepository;

    @Autowired
    private CustomerCartMenuRepository customerCartMenuRepository;

    @Autowired
    private RestaurantMenuRepository restaurantMenuRepository;

    @Override
    public Cart getLatestCartOfACustomer(String customerTenantId) {
        CustomerCart customerCart = customerCartRepository.findCartOfACustomer(customerTenantId);
        Cart cart = new Cart();
        if (customerCart == null) {
            return null;
        }
        else {
            cart.setCartId(customerCart.getCartId());
            cart.setCustomerTenantId(customerCart.getCustomerTenantId());
            cart.setRestaurantTenantId(customerCart.getRestaurantTenantId());
            cart.setCouponAppliedOnCart(customerCart.getCustomerCartCouponApplied());
            cart.setIsOrderPlaced(customerCart.getCustomerCartIsOrderPlaced());
            cart.setCartTotal(customerCart.getCartTotal());
            cart.setCartDeliveryFee(customerCart.getCartDeliveryFee());
            cart.setCreatedAt(customerCart.getCreatedAt());
            cart.setUpdatedAt(customerCart.getUpdatedAt());
            cart.setCartMenuList(buildCartItemListForCart(customerCart));
            return cart;
        }
    }

    @Override
    public Cart getCartDetails(Integer cartId) {
        CustomerCart customerCart = customerCartRepository.findCart(cartId);
        Cart cart = new Cart();
        if (customerCart == null) {
            return null;
        }
        else {
            cart.setCartId(cartId);
            cart.setCustomerTenantId(customerCart.getCustomerTenantId());
            cart.setRestaurantTenantId(customerCart.getRestaurantTenantId());
            cart.setCouponAppliedOnCart(customerCart.getCustomerCartCouponApplied());
            cart.setIsOrderPlaced(customerCart.getCustomerCartIsOrderPlaced());
            cart.setCartItemPriceTotal(customerCart.getCartItemPriceTotal());
            cart.setCartDiscountTotal(customerCart.getCartDiscountTotal());
            cart.setCartTotalCgstPercentage(customerCart.getCartTotalCgstPercentage());
            cart.setCartTotalSgstPercentage(customerCart.getCartTotalSgstPercentage());
            cart.setCartTotal(customerCart.getCartTotal());
            cart.setCartGrandTotal(customerCart.getCartGrandTotal());
            cart.setCartDeliveryFee(customerCart.getCartDeliveryFee());
            cart.setCreatedAt(customerCart.getCreatedAt());
            cart.setUpdatedAt(customerCart.getUpdatedAt());
            cart.setCartMenuList(buildCartItemListForCart(customerCart));
            return cart;
        }
    }

    private List<CartItem> buildCartItemListForCart(CustomerCart customerCart) {
        List<CustomerCartMenu> customerCartMenuList = customerCartMenuRepository.findCartMenuListForACart(customerCart.getCartId());
        List<CartItem> addedItemsInCart = new ArrayList<>();
        customerCartMenuList.stream().forEach( addedCartItem -> {
            RestaurantMenu menu = restaurantMenuRepository.findMenuDetailsForMenu(addedCartItem.getCartRestaurantMenuId());
            CartItem cartItem = new CartItem();
            cartItem.setCartId(customerCart.getCartId());
            cartItem.setCartMenuId(addedCartItem.getCartMenuId());
            cartItem.setCartRestaurantTenantId(menu.getRestaurantTenantId());
            cartItem.setCartRestaurantMenuName(menu.getRestaurantMenuName());
            cartItem.setCartRestaurantMenuDescription(menu.getRestaurantMenuDescription());
            cartItem.setCartRestaurantMenuType(menu.getRestaurantMenuType());
            cartItem.setCartRestaurantMenuCategory(menu.getRestaurantMenuCategory());
            cartItem.setCartRestaurantMenuPhoto(menu.getRestaurantMenuPhoto());
            cartItem.setCartRestaurantMenuPrice(menu.getRestaurantMenuPrice());
            cartItem.setCartRestaurantMenuPriceCgstPercentage(menu.getRestaurantMenuPriceCgstPercentage());
            cartItem.setCartRestaurantMenuPriceSgstPercentage(menu.getRestaurantMenuPriceSgstPercentage());
            cartItem.setCartRestaurantMenuRating(menu.getRestaurantMenuRating());
            cartItem.setCartRestaurantMenuFinalPrice(menu.getRestaurantMenuFinalPrice());
            cartItem.setCartRestaurantMenuQty(addedCartItem.getCartRestaurantMenuQty());
            addedItemsInCart.add(cartItem);
        });
        return addedItemsInCart;
    }

    @Override
    public Cart persistCustomerCart(Cart cartPayload) throws CartServiceException {
        CustomerCart presentCartOfCustomer = customerCartRepository.findCartOfACustomer(cartPayload.getCustomerTenantId());
        if (presentCartOfCustomer == null) {
            CustomerCart customerCart = buildCartFromPayload(cartPayload);
            CustomerCart persistedCartEntry = customerCartRepository.save(customerCart);
            if (persistedCartEntry == null) {
                throw new CartServiceException("Cart could not be persisted for customer: "+ cartPayload.getCustomerTenantId() + " and for restaurant: " + cartPayload.getRestaurantTenantId());
            }
            else {
                List<CustomerCartMenu> menuListForCart = buildCartMenuListFromPayload(cartPayload, persistedCartEntry);
                List<CustomerCartMenu> persistedCustomerCartMenuList = customerCartMenuRepository.saveAll((Iterable<CustomerCartMenu>) menuListForCart);
                if (persistedCustomerCartMenuList.isEmpty()) {
                    throw new CartServiceException("Cart Menu List could not be persisted for cart-id: "+ persistedCartEntry.getCartId());
                }
                else {
                    Cart persistedCart = buildPersistedCart(persistedCartEntry, persistedCustomerCartMenuList);
                    return persistedCart;
                }
            }
        }
        else {
            customerCartRepository.deleteLastCartOfACustomer(presentCartOfCustomer.getCustomerTenantId());
//            if (deletedCustomerCart == null) {
//                throw new CartServiceException("Existing Cart could not be deleted for customer: "+ cartPayload.getCustomerTenantId() + " and for restaurant: " + cartPayload.getRestaurantTenantId());
//            }
//            else {
                customerCartMenuRepository.deleteCartMenuListForACart(presentCartOfCustomer.getCartId());
//                if (deletedCustomerCartMenuList.isEmpty()) {
//                    throw new CartServiceException("Existing Cart Menu List could not be deleted for cart-id: " + deletedCustomerCart.getCartId());
//                } else {
                    CustomerCart customerCart = buildCartFromPayload(cartPayload);
                    CustomerCart persistedCartEntry = customerCartRepository.save(customerCart);
                    if (persistedCartEntry == null) {
                        throw new CartServiceException("Cart could not be persisted for customer: " + cartPayload.getCustomerTenantId() + " and for restaurant: " + cartPayload.getRestaurantTenantId());
                    } else {
                        List<CustomerCartMenu> menuListForCart = buildCartMenuListFromPayload(cartPayload, persistedCartEntry);
                        List<CustomerCartMenu> persistedCustomerCartMenuList = customerCartMenuRepository.saveAll((Iterable<CustomerCartMenu>) menuListForCart);
                        if (persistedCustomerCartMenuList.isEmpty()) {
                            throw new CartServiceException("Cart Menu List could not be persisted for cart-id: " + persistedCartEntry.getCartId());
                        } else {
                            Cart persistedCart = buildPersistedCart(persistedCartEntry, persistedCustomerCartMenuList);
                            return persistedCart;
                        }
                    }
//                }
//            }

        }
    }

    @Override
    public String updateOrderPlacedFlagForCart(Integer cartId) {
        try {
            customerCartRepository.updateIsOrderPlacedForCart(cartId);
        }
        catch (Exception e) {
            return FAILURE;
        }
        return SUCCESS;

    }

    private Cart buildPersistedCart(CustomerCart persistedCartEntry, List<CustomerCartMenu> persistedCustomerCartMenuList) {
        Cart persistedCart = new Cart();
        persistedCart.setCartId(persistedCartEntry.getCartId());
        persistedCart.setRestaurantTenantId(persistedCartEntry.getRestaurantTenantId());
        persistedCart.setCustomerTenantId(persistedCartEntry.getCustomerTenantId());
        persistedCart.setCouponAppliedOnCart(persistedCartEntry.getCustomerCartCouponApplied());
        persistedCart.setIsOrderPlaced(persistedCartEntry.getCustomerCartIsOrderPlaced());
        persistedCart.setCartMenuList(buildCartItemListForCart(persistedCartEntry));
        persistedCart.setCartItemPriceTotal(persistedCartEntry.getCartItemPriceTotal());
        persistedCart.setCartTotalCgstPercentage(persistedCartEntry.getCartTotalCgstPercentage());
        persistedCart.setCartTotalSgstPercentage(persistedCartEntry.getCartTotalSgstPercentage());
        persistedCart.setCartTotal(persistedCartEntry.getCartTotal());
        persistedCart.setCartDiscountTotal(persistedCartEntry.getCartDiscountTotal());
        persistedCart.setCartDeliveryFee(persistedCartEntry.getCartDeliveryFee());
        persistedCart.setCartGrandTotal(persistedCartEntry.getCartGrandTotal());
        persistedCart.setCreatedAt(persistedCartEntry.getCreatedAt());
        persistedCart.setUpdatedAt(persistedCartEntry.getUpdatedAt());
        return persistedCart;
    }

    private List<CustomerCartMenu> buildCartMenuListFromPayload(Cart cartPayload, CustomerCart persistedCartEntry) {
        List<CustomerCartMenu> menuListForCart = new ArrayList<>();
        cartPayload.getCartMenuList().stream().forEach(menuItem -> {
            CustomerCartMenu customerCartMenu = new CustomerCartMenu();
            customerCartMenu.setCartId(persistedCartEntry.getCartId());
            customerCartMenu.setCartRestaurantMenuId(menuItem.getCartRestaurantMenuId());
            customerCartMenu.setCartRestaurantMenuQty(menuItem.getCartRestaurantMenuQty());
            customerCartMenu.setCreatedAt(new Date());
            customerCartMenu.setUpdatedAt(new Date());
            menuListForCart.add(customerCartMenu);
        });
        return menuListForCart;
    }

    private CustomerCart buildCartFromPayload(Cart cartPayload) {
        CustomerCart customerCart = new CustomerCart();
        customerCart.setRestaurantTenantId(cartPayload.getRestaurantTenantId());
        customerCart.setCustomerTenantId(cartPayload.getCustomerTenantId());
        customerCart.setCustomerCartCouponApplied(cartPayload.getCouponAppliedOnCart() != null ? cartPayload.getCouponAppliedOnCart() : null);
        customerCart.setCustomerCartIsOrderPlaced(cartPayload.getIsOrderPlaced());
        customerCart.setCartItemPriceTotal(calculateItemPriceTotalOfCart(cartPayload.getCartMenuList()));
        customerCart.setCartTotalCgstPercentage(cartPayload.getCartTotalCgstPercentage());
        customerCart.setCartTotalSgstPercentage(cartPayload.getCartTotalSgstPercentage());
        customerCart.setCartTotal(calculateTotalOfCart(cartPayload.getCartMenuList()));
        customerCart.setCartDeliveryFee(cartPayload.getCartDeliveryFee());
        customerCart.setCartDiscountTotal(
                cartPayload.getCouponAppliedOnCart() != null ?
                       BigDecimal.valueOf(customerCart.getCartTotal())
                               .multiply(BigDecimal.valueOf(findDiscountValueForCouponApplied(cartPayload.getCouponAppliedOnCart())))
                                .setScale(2, RoundingMode.HALF_UP).doubleValue() :
                        0D);
        customerCart.setCartGrandTotal(calculateGrandTotalOfCart(customerCart.getCartTotal(),
                cartPayload.getCartDeliveryFee(),
                customerCart.getCartDiscountTotal()));
        customerCart.setCreatedAt(new Date());
        customerCart.setUpdatedAt(new Date());
        return customerCart;
    }

    private Double calculateTotalOfCart(List<CartItem> cartMenuList) {
        final BigDecimal[] bcartTotal = {BigDecimal.ZERO};
        cartMenuList.stream().forEach( item -> {
            BigDecimal cartItemFinalPrice = new BigDecimal(item.getCartRestaurantMenuFinalPrice());
            BigDecimal cartItemQty = new BigDecimal(item.getCartRestaurantMenuQty());
            bcartTotal[0] = bcartTotal[0].add(cartItemFinalPrice.multiply(cartItemQty).setScale(2, RoundingMode.HALF_UP))
                                .setScale(2, RoundingMode.HALF_UP);
        });
        return bcartTotal[0].doubleValue();
    }

    private Double calculateItemPriceTotalOfCart(List<CartItem> cartMenuList) {
        final BigDecimal[] bcartTotal = {BigDecimal.ZERO};
        cartMenuList.stream().forEach( item -> {
            BigDecimal cartItemPrice = new BigDecimal(item.getCartRestaurantMenuPrice());
            BigDecimal cartItemQty = new BigDecimal(item.getCartRestaurantMenuQty());
            bcartTotal[0] = bcartTotal[0].add(cartItemPrice.multiply(cartItemQty).setScale(2, RoundingMode.HALF_UP))
                    .setScale(2, RoundingMode.HALF_UP);
        });
        return bcartTotal[0].doubleValue();
    }

//    private Double calculateTotalOfCart(Double cartItemPriceTotal, Double cartTotalCgst, Double cartTotalSgst) {
//        BigDecimal bcartItemPriceTotal = new BigDecimal(cartItemPriceTotal);
//        BigDecimal bcartTotalCgst = new BigDecimal(cartTotalCgst);
//        BigDecimal bcartTotalSgst = new BigDecimal(cartTotalSgst);
//        BigDecimal bcartTotal = bcartItemPriceTotal.add(bcartTotalCgst.add(bcartTotalSgst)).setScale(2, RoundingMode.HALF_UP);
//        return bcartTotal.doubleValue();
//    }

    private Double calculateGrandTotalOfCart(Double cartTotal, Double cartDeliveryFee, Double cartDiscountTotal) {
        BigDecimal bcartTotal = new BigDecimal(cartTotal);
        BigDecimal bcartDeliveryFee = new BigDecimal(cartDeliveryFee);
        BigDecimal bcartDiscountTotal = new BigDecimal(cartDiscountTotal);
        BigDecimal bcartGrandTotal = (bcartTotal.add(bcartDeliveryFee)).subtract(bcartDiscountTotal).setScale(2, RoundingMode.HALF_UP);
        return bcartGrandTotal.doubleValue();
    }

    private Double findDiscountValueForCouponApplied(String couponAppliedOnCart) {
        return IMMUTABLE_MAP_OF_COUPON_TO_DISCOUNT.get(couponAppliedOnCart) == null ?
                0D :
                IMMUTABLE_MAP_OF_COUPON_TO_DISCOUNT.get(couponAppliedOnCart) ;
    }
}
