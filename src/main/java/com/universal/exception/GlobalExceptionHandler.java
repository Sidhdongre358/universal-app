package com.universal.exception;

import com.universal.dto.ErrorResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    // methods to handle exceptions

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatusCode status, WebRequest webRequest) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (existing, replacement) -> existing
                ));


        log.error(ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponseDto.setErrorMessage(ex.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());

        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDto);
    }


    @ExceptionHandler(TaskAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleTaskAlreadyExistsException(TaskAlreadyExistsException
                                                                                     ex, WebRequest webRequest) {


        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setStatus(HttpStatus.CONFLICT);
        errorResponseDto.setErrorMessage(ex.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());

        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponseDto);

    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException
                                                                                    ex, WebRequest webRequest) {


        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setStatus(HttpStatus.NOT_FOUND);
        errorResponseDto.setErrorMessage(ex.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());

        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);

    }

}
