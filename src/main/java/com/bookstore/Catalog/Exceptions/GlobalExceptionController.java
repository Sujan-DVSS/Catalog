package com.bookstore.Catalog.Exceptions;


import com.bookstore.Catalog.Response.ProductNotFoundExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionController {


// Custom Exception Handler
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody

    public ProductNotFoundExceptionResponse bookNotFound(ProductNotFoundException productNotFoundException){
        ProductNotFoundExceptionResponse productNotFoundExceptionResponse = new ProductNotFoundExceptionResponse(productNotFoundException.getErrorMessage());
        return productNotFoundExceptionResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String,String> missingFields(MethodArgumentNotValidException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", "Missing Required Fields");
        return error;
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody

    public Map<String,String> bookAlreadyExists(ProductAlreadyExistsException productAlreadyExistsException ){
        Map<String,String> error = new HashMap<>();
        error.put("message", productAlreadyExistsException.getErrorMessage());
        return error;
    }

}
