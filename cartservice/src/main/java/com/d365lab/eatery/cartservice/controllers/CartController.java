package com.d365lab.eatery.cartservice.controllers;

import com.d365lab.eatery.cartservice.model.Cart;
import com.d365lab.eatery.cartservice.services.CustomerCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.d365lab.eatery.cartservice.utils.CartServiceConstants.SUCCESS;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CartController {

    private final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CustomerCartService customerCartService;

    // Create or Update Cart Of A Customer
//    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/customer-cart/persist", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cart> persistCartOfACustomer(@RequestBody Cart cartPayload) {
        LOGGER.info(cartPayload.toString());
        Cart cart = null;
        try {
            cart = customerCartService.persistCustomerCart(cartPayload);
            LOGGER.info("Cart has been persisted successfully: {}", cart.toString());
        } catch (Exception e) {
            LOGGER.error("Exception:{} occurred while creating cart for customer:{}", e.getMessage(), cartPayload.getCustomerTenantId());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cart == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }


    // Get Latest Cart of a Customer
//    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/customer-cart/latest", method = RequestMethod.GET)
    public ResponseEntity<Cart> getCartOfACustomer(@RequestParam("customer_tenant_id") String customerTenantId) {
        Cart cart = null;
        cart = customerCartService.getLatestCartOfACustomer(customerTenantId);
        if (cart == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    // Get Cart Details
//    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/customer-cart", method = RequestMethod.GET)
    public ResponseEntity<Cart> getCartDetails(@RequestParam("cart_id") String cartId) {
        Cart cart = null;
        cart = customerCartService.getCartDetails(Integer.parseInt(cartId));
        if (cart == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }


    // Update order-placed flag to TRUE for cart of a customer
//    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/customer-cart/order-placed/update")
    public ResponseEntity<String> updateOrderPlacedFlagForCartOfACustomer(@RequestParam("cart_id") Integer cartId) {
        String status;
        try {
            status = customerCartService.updateOrderPlacedFlagForCart(cartId);
        } catch (Exception e) {
            LOGGER.error("Exception:{} occurred while updating order-placed flag for cart for customer:{}", e.getMessage(), cartId.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (status.compareToIgnoreCase(SUCCESS) == 0) {
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
