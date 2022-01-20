package com.devsuperior.dscatalog.services.exceptions;

public class ObjetcNotFoundException extends RuntimeException{
    public ObjetcNotFoundException(String msg){
        super(msg);
    }

    public ObjetcNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
