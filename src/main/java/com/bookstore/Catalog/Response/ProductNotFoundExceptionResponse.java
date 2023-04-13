package com.bookstore.Catalog.Response;

import com.bookstore.Catalog.Exceptions.ProductNotFoundException;

public class ProductNotFoundExceptionResponse {
    String errorMessage;

    public ProductNotFoundExceptionResponse(){

    }
    public ProductNotFoundExceptionResponse(String message){
        errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
