package com.springboot.taskmanagement.mapper;

import com.springboot.taskmanagement.dto.request.TaskReqDto;
import com.springboot.taskmanagement.dto.response.TaskResDto;
import com.springboot.taskmanagement.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public static Task convertDtoToEntity(final TaskReqDto taskReqDto) {
        Task task = new Task();
        task.setTitle(taskReqDto.title());
        task.setDescription(taskReqDto.description());
        task.setDueDate(taskReqDto.dueDate());
        task.setPriority(taskReqDto.priority());
        task.setStatus(taskReqDto.status());
        return task;
    }

    public static TaskResDto convertEntityToDto(Task task) {
        return new TaskResDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}