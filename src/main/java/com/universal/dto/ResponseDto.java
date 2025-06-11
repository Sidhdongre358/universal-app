package com.universal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "ResponseDto")
public class ResponseDto {
    @Schema(name = "statusCode", description = "Status code of the response", required = true)
    private String statusCode;
    @Schema(name = "message", description = "Message of the response", required = true)
    private String message;
}
