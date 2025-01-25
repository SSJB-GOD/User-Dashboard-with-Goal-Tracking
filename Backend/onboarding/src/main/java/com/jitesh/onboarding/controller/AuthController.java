package com.jitesh.onboarding.controller;

import com.jitesh.onboarding.dto.ForgotPasswordDto;
import com.jitesh.onboarding.dto.LoginDto;
import com.jitesh.onboarding.dto.RegisterDto;
import com.jitesh.onboarding.entites.User;
import com.jitesh.onboarding.repository.UserRepository;
import com.jitesh.onboarding.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3001")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    // Get person by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> person(@PathVariable int id, Authentication auth) {
        var response = new HashMap<String, Object>();


        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            response.put("Id", user.getId());  // Assuming your Person has a getId() method
            response.put("Authorities", auth.getAuthorities());
            response.put("Person", user);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();  // If no person is found
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDto registerDto, BindingResult result) {

        if (result.hasErrors()) {
            var errorMap = result.getAllErrors().stream()
                    .collect(Collectors.toMap(
                            error -> ((FieldError) error).getField(),
                            ObjectError::getDefaultMessage
                    ));
            return ResponseEntity.badRequest().body(errorMap);
        }

        Map<String, Object> response = userService.registerUser(registerDto);
        if (response.containsKey("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto, BindingResult result) {

        if (result.hasErrors()) {
            var errorMap = result.getAllErrors().stream()
                    .collect(Collectors.toMap(
                            error -> ((FieldError) error).getField(),
                            ObjectError::getDefaultMessage
                    ));
            return ResponseEntity.badRequest().body(errorMap);
        }
        Map<String, Object> response = new HashMap<>();
        if (userService.authenticateUser(loginDto).containsKey("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        Optional<User> person = userRepository.findByEmail(loginDto.getEmail());
        response.put("id", person.get().getId());

        return ResponseEntity.ok(response);
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto, BindingResult result) {
        if (result.hasErrors()) {
            var errorMap = result.getAllErrors().stream()
                    .collect(Collectors.toMap(
                            error -> ((FieldError) error).getField(),
                            ObjectError::getDefaultMessage
                    ));
            return ResponseEntity.badRequest().body(errorMap);
        }

        Map<String, Object> response = userService.forgotPassword(forgotPasswordDto.getEmail());
        if (response.containsKey("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }



}