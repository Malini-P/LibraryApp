package com.springboot.taskmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AdminDto(
        @NotBlank(message = "Field is mandatory")
        String username,
        @NotBlank(message = "Field is mandatory")
        String password
) {
}
