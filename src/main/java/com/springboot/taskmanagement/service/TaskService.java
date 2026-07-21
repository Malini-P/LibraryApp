package com.springboot.taskmanagement.service;

import com.springboot.taskmanagement.dto.request.TaskReqDto;
import com.springboot.taskmanagement.dto.response.TaskResDto;
import com.springboot.taskmanagement.exception.ResourceNotFoundException;
import com.springboot.taskmanagement.mapper.TaskMapper;
import com.springboot.taskmanagement.model.Task;
import com.springboot.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public void insert(TaskReqDto taskReqDto) {
        // Step 1: Map DTO to Entity
        Task task = TaskMapper.convertDtoToEntity(taskReqDto);
        // Step 2: Save to DB
        taskRepository.save(task);
    }

    public List<TaskResDto> getAll() {
        List<Task> list = taskRepository.findAll();
        return list
                .stream()
                .map(TaskMapper::convertEntityToDto)
                .toList();
    }

    public TaskResDto getById(long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task id invalid."));
        return TaskMapper.convertEntityToDto(task);
    }

    public void update(long id, TaskReqDto taskReqDto) {
        // Step 1: Fetch existing task
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task id invalid."));

        // Step 2: Update fields
        task.setTitle(taskReqDto.title());
        task.setDescription(taskReqDto.description());
        task.setDueDate(taskReqDto.dueDate());
        task.setPriority(taskReqDto.priority());
        task.setStatus(taskReqDto.status());

        // Step 3: Save (update)
        taskRepository.save(task);
    }

    public void delete(long id) {
        // Step 1: Check existence first
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task id invalid."));
        // Step 2: Delete
        taskRepository.delete(task);
    }
}
