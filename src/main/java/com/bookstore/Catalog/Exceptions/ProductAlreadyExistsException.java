package com.bookstore.Catalog.Exceptions;

public class ProductAlreadyExistsException extends RuntimeException{
    String bookCode;
    String errorMessage;

    public ProductAlreadyExistsException(String code){
        bookCode = code;
        errorMessage = new String("This product " + code + " already exists in DB");
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
