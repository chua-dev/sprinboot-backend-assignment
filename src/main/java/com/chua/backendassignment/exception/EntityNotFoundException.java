package com.chua.backendassignment.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class EntityNotFoundException extends RuntimeException{

    private final String entityName;
    private final String fieldName;
    private final Object fieldValue;

    public EntityNotFoundException(String entityName, String fieldName, Object fieldValue){
        super(String.format("%s not found with %s : '%s'", entityName, fieldName, fieldValue));
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
