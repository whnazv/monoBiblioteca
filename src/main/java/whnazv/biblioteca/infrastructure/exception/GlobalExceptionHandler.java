package whnazv.biblioteca.infrastructure.exception;

import whnazv.biblioteca.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Manejador global de excepciones para la capa REST.
 * Centraliza la traducción de excepciones de dominio a respuestas HTTP.
 * Preparado para ampliarse con nuevos tipos de errores.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends RuntimeException>, HttpStatus> STATUS_MAP = Map.ofEntries(
            Map.entry(NotFoundException.class, HttpStatus.NOT_FOUND),
            Map.entry(ValidationException.class, HttpStatus.BAD_REQUEST),
            Map.entry(BadRequestException.class, HttpStatus.BAD_REQUEST),
            Map.entry(ImageUploadException.class, HttpStatus.BAD_REQUEST),
            Map.entry(InsufficientStockException.class, HttpStatus.CONFLICT),
            Map.entry(UserAlreadyExistsException.class, HttpStatus.CONFLICT),
            Map.entry(BookAlreadyExistsException.class, HttpStatus.CONFLICT),
            Map.entry(AuthenticationException.class, HttpStatus.UNAUTHORIZED),
            Map.entry(AuthorizationException.class, HttpStatus.FORBIDDEN),
            Map.entry(OperationNotAllowedException.class, HttpStatus.FORBIDDEN),
            Map.entry(SaleProcessingException.class, HttpStatus.INTERNAL_SERVER_ERROR)
    );

    /**
     * Maneja excepciones de dominio y las convierte en respuestas JSON.
     *
     * @param ex excepción lanzada
     * @return respuesta HTTP con código y mensaje
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRestExceptions(RuntimeException ex) {
        HttpStatus status = STATUS_MAP.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(status.value(), ex.getMessage()));
    }

    /**
     * Representación estándar de error para respuestas REST.
     *
     * @param status código HTTP
     * @param message mensaje de error
     */
    public record ErrorResponse(int status, String message) { }
}
