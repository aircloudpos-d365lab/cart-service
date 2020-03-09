package com.d365lab.eatery.cartservice.utils;

import com.google.common.collect.ImmutableMap;

public class CartServiceConstants {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
    public static final ImmutableMap<String, Double> IMMUTABLE_MAP_OF_COUPON_TO_DISCOUNT = ImmutableMap.<String, Double>builder()
            .put("SPECIAL27OFF", 0.27)
            .build();
}
