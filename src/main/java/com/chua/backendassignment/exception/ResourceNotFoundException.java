package com.chua.backendassignment.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private final String entityName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundException(String entityName, String fieldName, Object fieldValue){
        super(String.format("%s not found with %s : '%s'", entityName, fieldName, fieldValue));
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
