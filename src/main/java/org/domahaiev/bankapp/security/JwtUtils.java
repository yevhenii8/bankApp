package org.domahaiev.bankapp.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${variables.jwtSecret}")
    private String jwtSecret;

    @Value("${variables.jwtExpirationMS}")
    private int jwtExpirationMS;

    public String generateJWTUserDetails(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return generateJWT(userDetails.getUsername(), Collections.emptyMap(), jwtExpirationMS);
    }

    public String generateJWT(String sub, Map<String, Object> claims, int timeLive) {
        return Jwts.builder()
                .setSubject(sub)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + timeLive))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public Claims getBody(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException | SecurityException e) {
            System.out.println("Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }
}
