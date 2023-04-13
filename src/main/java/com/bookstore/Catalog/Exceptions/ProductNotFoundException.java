package com.bookstore.Catalog.Exceptions;

import org.springframework.http.HttpStatus;



public class ProductNotFoundException extends RuntimeException{
    int errorCode;
    String errorMessage;
    String bookCode;

    public ProductNotFoundException(String message){
        errorMessage = message;
    }

    public int getErrorCode(int bookCode) {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }
}
