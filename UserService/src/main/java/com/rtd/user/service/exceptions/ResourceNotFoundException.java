package com.rtd.user.service.exceptions;

import javax.management.relation.RoleNotFoundException;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("Resource not found on server");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }

}
