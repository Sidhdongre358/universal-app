package com.universal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ErrorResponseDto", description = "Error response dto")
public class ErrorResponseDto {
    @Schema(name = "apiPath", description = "API path", required = true)
    private String apiPath;
    @Schema(name = "statusCode", description = "Status code of the error", required = true)
    private HttpStatus status;
    @Schema(name = "errorMessage", description = "Error message", required = true)
    private String errorMessage;
    @Schema(name = "errorTime", description = "Time of the error", required = true)
    private LocalDateTime errorTime;
}