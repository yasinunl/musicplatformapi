package com.domain.musicplatform.controller;

import com.domain.musicplatform.dto.ExceptionResponseDTO;
import com.domain.musicplatform.exception.BadExceptionHandler;
import com.domain.musicplatform.exception.SuccessExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> exceptionResponseEntity(BadExceptionHandler exc){
        ExceptionResponseDTO error = new ExceptionResponseDTO(
                exc.getMessage(),
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> exceptionResponseEntity(SuccessExceptionHandler exc){
        ExceptionResponseDTO error = new ExceptionResponseDTO(
                exc.getMessage(),
                System.currentTimeMillis(),
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> handleError(Exception exc){
        ExceptionResponseDTO error = new ExceptionResponseDTO(
                exc.getMessage(),
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
