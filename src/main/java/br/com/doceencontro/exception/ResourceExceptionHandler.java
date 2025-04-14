package br.com.doceencontro.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.doceencontro.exception.exceptions.ConflictException;
import br.com.doceencontro.exception.exceptions.EmailEmUsoException;
import br.com.doceencontro.exception.exceptions.EventoNotFoundException;
import br.com.doceencontro.exception.exceptions.ForbiddenException;
import br.com.doceencontro.exception.exceptions.NotFoundException;
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
	
	@ExceptionHandler(EmailEmUsoException.class)
    public ResponseEntity<Map<String, String>> responseEntity(EmailEmUsoException ex) {
        Map<String, String> erro = new HashMap<>();
        
        erro.put("email", ex.getMessage());

        return ResponseEntity.badRequest().body(erro);
    }
}



