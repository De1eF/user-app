package com.example.userapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRequestDto {
    @NotBlank
    @Length(min = 3, max = 16)
    @Pattern(regexp = "\\w+")
    private String username;
    @NotBlank
    @Length(min = 3, max = 16)
    @Pattern(regexp = "\\w+")
    @Pattern(regexp = "[0-9]")
    private String password;
    @NotBlank
    @Length(min = 1, max = 16)
    @Pattern(regexp = "\\w+")
    private String firstName;
    @NotBlank
    @Length(min = 1, max = 16)
    @Pattern(regexp = "\\w+")
    private String lastName;
}
