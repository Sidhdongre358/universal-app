package com.universal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "RegisterRequest", description = "User registration request")
public class RegisterRequest {
    @NotEmpty(message = "Name is required")
    @Schema(description = "User name", example = "John Doe")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Schema(description = "User email", example = "x4eGc@example.com")
    @Email(message = "Invalid email format")
    private String email;
    @NotEmpty(message = "Password is required")
    @Schema(description = "User password", example = "password123")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,50}$", message = "Password must contain at least one letter and one number")
    private String password;
    @NotEmpty(message = "Role is required")
    @Schema(description = "User role", example = "USER")
    @Size(min = 3, max = 50, message = "Role must be between 3 and 50 characters")
    private String role;  // USER, ADMIN, DRIVER etc.
}
