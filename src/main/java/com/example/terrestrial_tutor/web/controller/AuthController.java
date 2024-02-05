package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.User;
import com.example.terrestrial_tutor.entity.enums.ERole;
import com.example.terrestrial_tutor.payload.request.LoginRequest;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;
import com.example.terrestrial_tutor.payload.response.JWTTokenSuccessResponse;
import com.example.terrestrial_tutor.repository.PupilRepository;
import com.example.terrestrial_tutor.security.JWTTokenProvider;
import com.example.terrestrial_tutor.security.SecurityConstants;
import com.example.terrestrial_tutor.service.CheckService;
import com.example.terrestrial_tutor.service.PupilService;
import com.example.terrestrial_tutor.service.UserService;
import com.example.terrestrial_tutor.validators.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Api
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    JWTTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private UserService userService;
    @Autowired
    PupilService pupilService;
    @Autowired
    CheckService checkService;

    @PostMapping("/auth/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    @PostMapping("/auth/registration")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult bindResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindResult);
        if (!ObjectUtils.isEmpty(errors)) {
            System.out.println(errors);
            return errors;
        }

        User newUser = userService.createUser(registrationRequest);
        switch (registrationRequest.getRole()) {
            case PUPIL:
                Long pupilId = pupilService.addNewPupil(new PupilEntity()).getId();
                newUser.setAdditionalInfoId(pupilId);
                userService.updateUser(newUser);
        }
        checkService.addCheck(newUser);

        return new ResponseEntity<>("User successfully created", HttpStatus.OK);
    }

}
