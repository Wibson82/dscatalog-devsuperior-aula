package com.devsuperior.dscatalog.services.exceptions;

import java.io.Serial;

public class ObjetcNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public ObjetcNotFoundException(String msg){
        super(msg);
    }

    public ObjetcNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
