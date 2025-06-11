package com.universal.service;

import com.universal.dto.TaskDto;
import com.universal.entity.Task;

import java.util.List;

public interface ITaskService {

    // perform CRUD operations

    // get all tasks
    List<TaskDto> getAllTasks();

    // get tasks by status
    List<TaskDto> getTasksByStatus(String status);


    // get task by id
    TaskDto getTaskById(Long id);

    // create task
    void createTask(TaskDto taskDto);

    // update task
    boolean updateTask(Long id, TaskDto taskDto);

    // delete task
    boolean deleteTask(Long id);

    List<TaskDto> getTasksByUserId(Long userId);
}
