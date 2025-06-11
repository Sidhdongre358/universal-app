package com.universal.service;

import com.universal.dto.TaskDto;
import com.universal.entity.Task;
import com.universal.exception.ResourceNotFoundException;
import com.universal.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TaskDto> getAllTasks() {
        log.info("getAllTasks");
        List<Task> tasks = taskRepository.findAll();

        if (tasks.isEmpty()) {
            log.info("No tasks found");

            throw new RuntimeException("No tasks found");

        }

        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .toList();
    }

    @Override
    public List<TaskDto> getTasksByStatus(String status) {
        log.info("getTasksByStatus");


        if (status != null) {
            return taskRepository.findAllByStatus(status)
                    .stream()
                    .map(task -> modelMapper.map(task, TaskDto.class))
                    .toList();
        }

        return List.of();
    }

    @Override
    public TaskDto getTaskById(Long id) {

        log.info("getTaskById");
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public void createTask(TaskDto taskDto) {

        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.save(task);


    }


    @Override
    public boolean updateTask(Long id, TaskDto taskDto) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Task not found for this id {}", id);
                    return new ResourceNotFoundException("Task", "id", id);
                });

        modelMapper.map(taskDto, task);
        taskRepository.save(task);


        return true;
    }

    @Override
    public boolean deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Task not found for this id {}", id);
                    return new ResourceNotFoundException("Task", "id", id);
                });
        taskRepository.delete(task);
        return true;
    }

    @Override
    public List<TaskDto> getTasksByUserId(Long userId) {

log.info("getTasksByUserId");
List<Task> tasks =  taskRepository.findAllByUserId(userId);

        if (tasks.isEmpty()) {
            log.info("No tasks found");
            throw new RuntimeException("No tasks found");
        }


           return     tasks .stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .toList();



    }
}
