package com.springboot.taskmanagement.service;

import com.springboot.taskmanagement.dto.request.TaskReqDto;
import com.springboot.taskmanagement.dto.response.TaskResDto;
import com.springboot.taskmanagement.enums.Priority;
import com.springboot.taskmanagement.enums.Status;
import com.springboot.taskmanagement.exception.ResourceNotFoundException;
import com.springboot.taskmanagement.model.Task;
import com.springboot.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private TaskReqDto taskReqDto;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Finish report");
        task.setDescription("Complete Q1 report");
        task.setDueDate(LocalDate.of(2026, 7, 25));
        task.setPriority(Priority.HIGH);
        task.setStatus(Status.PENDING);

        taskReqDto = new TaskReqDto(
                "Finish report",
                "Complete Q1 report",
                LocalDate.of(2026, 7, 25),
                Priority.HIGH,
                Status.PENDING
        );
    }

    @Test
    void testInsert() {
        // Step 1: Stub the save call
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Step 2: Call the actual method
        taskService.insert(taskReqDto);

        // Step 3: Verify save was called exactly once
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetAll() {
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<TaskResDto> result = taskService.getAll();

        assertEquals(1, result.size());
        assertEquals("Finish report", result.get(0).title());
    }

    @Test
    void testGetById_Success() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskResDto result = taskService.getById(1L);

        assertNotNull(result);
        assertEquals("Finish report", result.title());
        assertEquals(Priority.HIGH, result.priority());
    }

    @Test
    void testGetById_NotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> taskService.getById(99L));
    }

    @Test
    void testUpdate_Success() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        taskService.update(1L, taskReqDto);

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testUpdate_NotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> taskService.update(99L, taskReqDto));
    }

    @Test
    void testDelete_Success() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);

        taskService.delete(1L);

        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    void testDelete_NotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> taskService.delete(99L));
    }
}
