package com.jitesh.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordDto {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Override
    public String toString() {
        return "ForgotPasswordDto{" +
                "email='" + email + '\'' +
                '}';
    }

    public @Email(message = "Invalid email format") @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public ForgotPasswordDto(String email) {
        this.email = email;
    }

    public ForgotPasswordDto() {
    }

    public void setEmail(@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email) {
        this.email = email;
    }
}
