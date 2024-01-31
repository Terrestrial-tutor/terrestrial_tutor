package com.example.terrestrial_tutor.security;

import com.example.terrestrial_tutor.entity.User;
import io.jsonwebtoken.*;
import org.slf4j.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

@Component
public class JWTTokenProvider {
    public static final Logger LOG = LoggerFactory.getLogger(JWTTokenProvider.class);

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        String userId = Long .toString(user.getId());

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", userId);
        claimsMap.put("email", user.getEmail());
        claimsMap.put("name", user.getName());
        claimsMap.put("surname", user.getSurname());
        claimsMap.put("patronymic", user.getPatronymic());
        claimsMap.put("password", user.getPassword());
        claimsMap.put("role", user.getRole());

        return Jwts.builder()
                .setSubject(userId)
                .addClaims(claimsMap)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    public boolean validateToken (String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJwt(token);
            return true;
        } catch (MalformedJwtException |
                 UnsupportedJwtException |
                 IllegalFormatException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }

}
