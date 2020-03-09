package com.d365lab.eatery.cartservice.services;

import com.d365lab.eatery.cartservice.model.Cart;
import com.d365lab.eatery.cartservice.utils.exceptions.CartServiceException;
import org.springframework.stereotype.Service;

@Service
public interface CustomerCartService {
    Cart getLatestCartOfACustomer(String customerTenantId);

    Cart getCartDetails(Integer cartId);

    Cart persistCustomerCart(Cart cartPayload) throws CartServiceException;

    String updateOrderPlacedFlagForCart(Integer cartId);
}
