package com.api.enchentes.common.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler {

    // 400 – Validação de @Valid em corpos JSON (DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        var fieldError = ex.getBindingResult().getFieldErrors().stream().findFirst();
        var message = fieldError
                .map(fe -> fe.getField() + " " + fe.getDefaultMessage())
                .orElse("Requisição inválida");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.of(message, HttpStatus.BAD_REQUEST));
    }

    // 400 – Validação de parâmetros (ex.: @RequestParam, @PathVariable)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
        var message = ex.getConstraintViolations().stream().findFirst()
                .map(v -> v.getPropertyPath() + " " + v.getMessage())
                .orElse("Parâmetros inválidos");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.of(message, HttpStatus.BAD_REQUEST));
    }

    // 400 – Tipo de argumento inválido (ex.: converteu string para number e falhou)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Parâmetro '%s' inválido".formatted(ex.getName());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.of(message, HttpStatus.BAD_REQUEST));
    }

    // 400 – Erros de regra de negócio simples
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.of(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    // 401 – Falha de autenticação (login inválido, token ausente/inválido)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthentication(AuthenticationException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiError.of("Credenciais inválidas ou não fornecidas", HttpStatus.UNAUTHORIZED));
    }

    // 403 – Sem permissão (falta de ROLE, etc.)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiError.of("Acesso negado", HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex) {
        String message = "Conflito de dados. Verifique se já existe um registro com esses valores.";
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiError.of(message, HttpStatus.CONFLICT));
    }

    // 500 – Fallback (qualquer coisa não tratada acima)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        String message = "Erro interno inesperado";
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiError.of(message, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
