package com.springboot.taskmanagement.controller;

import com.springboot.taskmanagement.dto.request.TaskReqDto;
import com.springboot.taskmanagement.dto.response.TaskResDto;
import com.springboot.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public void insert(@Valid @RequestBody TaskReqDto taskReqDto) {
        taskService.insert(taskReqDto);
    }

    @GetMapping("/all")
    public List<TaskResDto> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public TaskResDto getById(@PathVariable long id) {
        return taskService.getById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @Valid @RequestBody TaskReqDto taskReqDto) {
        taskService.update(id, taskReqDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        taskService.delete(id);
    }
}
