package com.bilalbererk.Dukkan.Exceptions;

import com.bilalbererk.Dukkan.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions( MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        ErrorResponse errorResponse = new ErrorResponse("USER_ALREADY_EXISTS", ex.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(errorResponse);
    }

    @ExceptionHandler(UserDidNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserDidNotExistException(UserDidNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("USER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(errorResponse);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFoundException(AddressNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse("ADDRESS_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(errorResponse);
    }

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleUserNotAuthenticatedException(UserNotAuthenticatedException ex){
        ErrorResponse errorResponse = new ErrorResponse("USER_NOT_AUTHENTICATED",ex.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(errorResponse);
    }

    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleShopNotFoundException(ShopNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse("SHOP_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(errorResponse);
    }

    @ExceptionHandler(UserNotAuthorizedActionException.class)
    public ResponseEntity<ErrorResponse> handleUserNotAuthorizedException(UserNotAuthorizedActionException ex){
        ErrorResponse errorResponse = new ErrorResponse("NOT_AUTHORIZED", ex.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(errorResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse>  handleProductNotFoundException(ShopNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse("PRODUCT_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(errorResponse);
    }

}
