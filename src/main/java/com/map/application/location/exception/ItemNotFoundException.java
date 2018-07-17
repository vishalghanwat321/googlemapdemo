package com.map.application.location.exception;


/*
 *
 *  @project application
 *
 *  @author vishal on 11/07/18  11:20 PM
 *
 */


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item not found")
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
