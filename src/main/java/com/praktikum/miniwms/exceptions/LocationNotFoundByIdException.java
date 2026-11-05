package com.praktikum.miniwms.exceptions;

public class LocationNotFoundByIdException extends RuntimeException{
    public LocationNotFoundByIdException(String message){
        super(message);
    }
}
