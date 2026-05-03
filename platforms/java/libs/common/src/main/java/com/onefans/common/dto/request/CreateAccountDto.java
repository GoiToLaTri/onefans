package com.onefans.common.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountDto {
    @NotBlank(message = "REQUIRED_NAME")
    @Size(min = 1, message = "INVALID_NAME")
    String name;

    @NotBlank(message = "REQUIRED_PASSWORD")
    @Size(min = 4, message = "INVALID_PASSWORD")
    String password;

    @NotBlank(message = "REQUIRED_EMAIL")
    @Email(message = "INVALID_EMAIL")
    String email;
}
