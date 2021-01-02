package com.abrajner.plagiarismdetector.common;

import java.security.Key;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.abrajner.plagiarismdetector.dao.entity.UserEntity;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    
    final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public static Long parseToken(final String token) {
        try {
            return Long.valueOf(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject());
        } catch (final JwtException | ClassCastException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
    
    public static String generateToken(final UserEntity u) {
        return Jwts.builder()
                .setSubject(u.getId().toString())
                .signWith(key)
                .compact();
    }
}
