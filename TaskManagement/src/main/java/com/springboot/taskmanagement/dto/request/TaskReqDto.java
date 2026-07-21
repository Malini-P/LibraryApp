package com.springboot.taskmanagement.dto.request;

import com.springboot.taskmanagement.enums.Priority;
import com.springboot.taskmanagement.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TaskReqDto(
        @NotBlank(message = "Field is mandatory")
        String title,
        @NotBlank(message = "Field is mandatory")
        String description,
        @NotNull(message = "Field is mandatory")
        LocalDate dueDate,
        @NotNull(message = "Field is mandatory")
        Priority priority,
        @NotNull(message = "Field is mandatory")
        Status status
) {
}
