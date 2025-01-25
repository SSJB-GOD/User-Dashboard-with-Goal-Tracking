package com.jitesh.onboarding.dto;

import jakarta.validation.constraints.NotEmpty;



public class RegisterDto {




    private String email;

    //    @Size(min = 6, message = "Minimum Password length is 6 characters")

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisterDto() {
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

