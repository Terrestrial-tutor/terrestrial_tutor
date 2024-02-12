package com.example.terrestrial_tutor.payload.response;

import com.example.terrestrial_tutor.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {
   private boolean success;
   private String token;
   private ERole role;
}
