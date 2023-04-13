package com.bookstore.Catalog.Exceptions;


import com.bookstore.Catalog.Response.ProductNotFoundExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody

    public ProductNotFoundExceptionResponse bookNotFound(ProductNotFoundException productNotFoundException){
        ProductNotFoundExceptionResponse productNotFoundExceptionResponse = new ProductNotFoundExceptionResponse(productNotFoundException.getErrorMessage());
        return productNotFoundExceptionResponse;
    }
}
