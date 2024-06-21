package com.purwadhika.montrackv2.auth.dto;

import com.purwadhika.montrackv2.users.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull
    @Min(1)
    private int activeCurrency;

    public User toEntity() {
        User user = new User();
        user.setDisplayName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
