package com.tenniscourts.exceptions;

import com.tenniscourts.domain.commons.DatabaseMessages;
import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> somethingWentWrong(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());

        ErrorDetails response = new ErrorDetails(LocalDateTime.now(), "Oh no, something went wrong on the Server", null);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(ArgumentValidator.class)
    public final ResponseEntity<ErrorDetails> argumentValidatorException(ArgumentValidator ex) {
        log.error(ex.getMessage());

        ErrorDetails response = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), null);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ErrorDetails> handleConflict(ConversionFailedException ex) {
        log.error(ex.getMessage());
        ErrorDetails response = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataValidationException.class)
    public final ResponseEntity<ErrorDetails> dataValidatorException(DataValidationException ex) {
        log.error(ex.getMessage());

        ErrorDetails response = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), null);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(DatabaseException.class)
    public final ResponseEntity<ErrorDetails> databaseException(DatabaseException ex) {
        String errorMessage = DatabaseMessages.translateMessage(ex);
        log.error(errorMessage);
        ex.printStackTrace();

        ErrorDetails response = new ErrorDetails(LocalDateTime.now(), errorMessage, null);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(AlreadyExistsEntityException.class)
    public final ResponseEntity<ErrorDetails> handleEntityAlreadyExists(AlreadyExistsEntityException ex, WebRequest request) {
        log.error(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ErrorDetails> handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), null);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.error(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new ErrorDetails(LocalDateTime.now(), error, ex.getLocalizedMessage()), BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new ErrorDetails(LocalDateTime.now(), builder.substring(0, builder.length() - 2), ex.getLocalizedMessage()), UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        log.error(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                "Constraint violation",
                ex.getConstraintViolations()
                        .stream()
                        .map(constraintViolation ->
                                constraintViolation.getPropertyPath() + " - " +
                                        constraintViolation.getMessage())
                        .collect(Collectors.joining()));
        return buildResponseEntity(errorDetails, BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorDetails error, HttpStatus status) {
        return new ResponseEntity<>(error, status);
    }

}
