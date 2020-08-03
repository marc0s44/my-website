package com.mywebsite.utility;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicDecoder {
    private static final String PREFIX = "Basic ";
    public static String[] decode(String encrypted) {
        if(encrypted == null || !encrypted.startsWith(PREFIX)) {
            throw new IllegalArgumentException("Provided wrong header");
        }
        String base64Credentials = encrypted.substring(PREFIX.length());
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        String[] result = credentials.split(":", 2);
        return result;
    }
}
