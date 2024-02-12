package com.example.terrestrial_tutor.security;
import com.example.terrestrial_tutor.entity.AdminEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.entity.enums.ERole;
import com.example.terrestrial_tutor.exceptions.NotVerificationException;
import io.jsonwebtoken.*;
import lombok.NonNull;
import org.slf4j.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

@Component
public class JWTTokenProvider {
    public static final Logger LOG = LoggerFactory.getLogger(JWTTokenProvider.class);

    public String generateToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();

        Map<String, Object> claimsMap = new HashMap<>();
        String userId;

        if (user instanceof PupilEntity pupil) {
            userId = Long.toString(pupil.getId());
            claimsMap = getClaims(userId, pupil.getEmail(), pupil.getRole(), pupil.getVerification());
        } else if (user instanceof TutorEntity tutor){
            userId = Long.toString(tutor.getId());
            claimsMap = getClaims(userId, tutor.getEmail(), tutor.getRole(), tutor.getVerification());
        } else {
            AdminEntity admin = (AdminEntity) user;
            userId = Long.toString(admin.getId());
            claimsMap.put("id", userId);
            claimsMap.put("email", admin.getEmail());
            claimsMap.put("role", admin.getRole());
        }

        return Jwts.builder()
                .setSubject(userId)
                .addClaims(claimsMap)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    @NonNull
    private Map<String, Object> getClaims(String userId, String email, ERole role, Boolean verification) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", userId);
        claimsMap.put("email", email);
        claimsMap.put("role", role);
        if (!verification) {
            LOG.error("User is not verification");
            throw new NotVerificationException("User is not verification");
        }
        return claimsMap;
    }

    public boolean validateToken (String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token);
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

    public ERole getUserRoleFromToken (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();
        Object role = claims.get("role");
        if (role.equals("ADMIN")) {
            return ERole.ADMIN;
        } else if (role.equals("PUPIL")) {
            return ERole.PUPIL;
        } else {
            return ERole.TUTOR;
        }
    }

}
