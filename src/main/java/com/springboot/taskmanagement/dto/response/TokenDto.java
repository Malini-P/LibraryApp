package com.springboot.taskmanagement.dto.response;

public record TokenDto(
        String token,
        String expiry
) {
}