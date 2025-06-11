package com.universal.controller;

import com.universal.constants.AppConstants;
import com.universal.dto.ResponseDto;
import com.universal.dto.TaskDto;
import com.universal.service.ITaskService;
import com.universal.utils.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Log4j2
@Tag(name = "CRUD Rest API for Task", description = "CRUD Rest API for Task")
@AllArgsConstructor
public class TaskController {

    private final ITaskService taskService;
    private final AuthUtil authUtil;


    @Operation(summary = "Create Task", description = "Create a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createTask(@Valid @RequestBody TaskDto taskDto) {

        log.info("Inside createTask method of TaskController");
        taskService.createTask(taskDto);
        log.info("Task created successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AppConstants.STATUS_CODE_201, "Task created successfully"));
    }


    @PreAuthorize("hasRole('ADMIN')")

    @Operation(summary = "Get All Tasks", description = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {


        log.info("Inside getAllTasks method of TaskController");

        List<TaskDto> tasks = taskService.getAllTasks();
        log.info("Tasks fetched successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tasks);

    }

    @Operation(summary = "Get Tasks of logged in user", description = "Get tasks of logged in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/user")
    public ResponseEntity<List<TaskDto>> getTasksOfLoggedInUser() {

        Long userId = authUtil.loggedInUserId();
        log.info("Inside getTasksByUserId method of TaskController");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.getTasksByUserId(userId));
    }

    @Operation(summary = "Get Tasks By Status", description = "Get tasks by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskDto>> getTasksByStatus(@PathVariable String status) {

        log.info("Inside getTasksByStatus method of TaskController");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.getTasksByStatus(status));
    }

    @Operation(summary = "Update Task", description = "Update task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update/{taskId}")
    public ResponseEntity<ResponseDto> updateTask(@PathVariable Long taskId, TaskDto taskDto) {
        log.info("Inside updateTask method of TaskController");
        taskService.updateTask(taskId, taskDto);
        log.info("Task updated successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AppConstants.STATUS_CODE_200, "Task updated successfully"));
    }

    @Operation(summary = "Get Task By Id", description = "Get task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/id/{taskId}")
    public ResponseEntity<ResponseDto> deleteTaskById(@PathVariable Long taskId) {
        log.info("Inside deleteTaskById method of TaskController");
        taskService.deleteTask(taskId);
        log.info("Task deleted successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AppConstants.STATUS_CODE_200, "Task deleted successfully"));
    }
}
