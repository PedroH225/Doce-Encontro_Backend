package com.example.festora.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.festora.exception.exceptions.ConflictException;
import com.example.festora.exception.exceptions.EventoNotFoundException;
import com.example.festora.exception.exceptions.ForbiddenException;
import com.example.festora.exception.exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(exception = NotFoundException.class)
	public ResponseEntity<StandardError> responseEntity(NotFoundException e, HttpServletRequest request) {
        String error = "Recurso não encontrado.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err); 
    }
	
	@ExceptionHandler(exception = ForbiddenException.class)
	public ResponseEntity<StandardError> responseEntity(ForbiddenException e, HttpServletRequest request) {
        String error = "Ação não permitida.";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err); 
    }
	
	@ExceptionHandler(exception = ConflictException.class)
	public ResponseEntity<StandardError> responseEntity(ConflictException e, HttpServletRequest request) {
        String error = "Ação inválida: conflito.";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err); 
    }
}



