package com.fishaquapets.petshop_api.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.Arrays;

@RestControllerAdvice
public class ResourceExceptionHandler {

    // Trata o erro 404 (Recurso não encontrado)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Recurso não encontrado",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    // Trata o erro 400 (Parâmetro inválido na URL, ex: Enum digitado errado)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> typeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = "O valor '" + e.getValue() + "' é inválido para o parâmetro '" + e.getName() + "'.";

        // Verifica se o erro foi causado por um Enum para mostrar os valores aceitos
        Class<?> requiredType = e.getRequiredType();
        if (requiredType != null && requiredType.isEnum()) {
            String acceptedValues = Arrays.toString(requiredType.getEnumConstants());
            message += " Valores aceitos são: " + acceptedValues;
        }

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Parâmetro inválido",
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}
