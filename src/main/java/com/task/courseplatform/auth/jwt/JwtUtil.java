package com.task.courseplatform.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.security.Key;


public class JwtUtil {

    // simple hardcoded key (OK for assignment)
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24 hours

    public static String generateToken(String email) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public static long getExpirationTime() {
        return EXPIRATION_TIME / 1000; // seconds
    }

    public static Key getKey() {
        return key;
    }

}
