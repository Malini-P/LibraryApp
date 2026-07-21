package com.springboot.taskmanagement.dto.response;

import com.springboot.taskmanagement.enums.Priority;
import com.springboot.taskmanagement.enums.Status;
import java.time.LocalDate;

public record TaskResDto(
        long id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        Status status
) {
}
