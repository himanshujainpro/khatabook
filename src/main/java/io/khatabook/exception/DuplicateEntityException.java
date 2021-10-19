package io.khatabook.exception;

public class DuplicateEntityException extends RuntimeException{
    public DuplicateEntityException(String message){
        super(message);
    }
}
