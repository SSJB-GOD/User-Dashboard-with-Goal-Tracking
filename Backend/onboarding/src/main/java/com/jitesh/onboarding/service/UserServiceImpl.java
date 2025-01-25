package com.jitesh.onboarding.service;

import com.jitesh.onboarding.dto.LoginDto;
import com.jitesh.onboarding.dto.RegisterDto;
import com.jitesh.onboarding.entites.User;
import com.jitesh.onboarding.repository.UserRepository;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

    // Registration logic
    public Map<String, Object> registerUser(RegisterDto registerDto) {
        var response = new HashMap<String, Object>();

        // Check if username or email exists
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            response.put("error", "Email already exists");
            return response;
        }

        // Encrypt password and save user
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setCreatedAt(new Date());
        user.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
        userRepository.save(user);

        response.put("token", createJwtToken(user));
        response.put("user", user);
        return response;
    }

    public Map<String, Object> forgotPassword(String email) {
        var response = new HashMap<String, Object>();
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            response.put("error", "Email not found");
            return response;
        }

        // Mock reset token or password reset link
        String resetToken = "RESET-" + System.currentTimeMillis(); // Simple mock token
        response.put("message", "Password reset link sent to your email");
        response.put("resetToken", resetToken);

        // Optionally, you can send an email (not implemented here for mock purposes)
        return response;
    }


    // Authentication logic
    public Map<String, Object> authenticateUser(@Valid LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());
        var response = new HashMap<String, Object>();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (bCryptEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                response.put("token", createJwtToken(user));
                response.put("user", user);
                return response;
            }
        }
        response.put("error", "Email or password is incorrect");
        return response;
    }

    // JWT creation logic
    private String createJwtToken(User user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600))  // 1 day expiration
                .subject(user.getEmail())
                .build();

        var encoder = new NimbusJwtEncoder(new ImmutableSecret<>(jwtSecretKey.getBytes()));
        var params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(params).getTokenValue();
    }

    // loadUserByUsername (existing method)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles("USER") // Add roles as necessary
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
