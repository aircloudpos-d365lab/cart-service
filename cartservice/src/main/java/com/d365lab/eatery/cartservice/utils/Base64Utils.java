package com.d365lab.eatery.cartservice.utils;

import java.util.Base64;

public class Base64Utils {

    public static String encodeBase64(byte [] encodeMe) {
        byte[] encodedBytes = Base64.getEncoder().encode(encodeMe);
        return new String(encodedBytes) ;
    }

    public static byte[] decodeBase64(String encodedData) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedData.getBytes());
        return decodedBytes ;
    }

}
