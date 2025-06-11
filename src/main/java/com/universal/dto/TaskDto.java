package com.universal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Task", description = "Task details")
public class TaskDto {

    @Schema(description = "Task ID", example = "1")
    @NotEmpty(message = "Title is required")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    private String title;
    @Schema(description = "Task description", example = "Task description")
    @NotEmpty(message = "Description is required")
    @Size(min = 2, max = 50, message = "Description must be between 2 and 50 characters")
    private String description;
    @Schema(description = "Task status", example = "TODO")
    @NotEmpty(message = "Status is required")
    @Size(min = 2, max = 50, message = "Status must be between 2 and 50 characters")
    private String status;
}
