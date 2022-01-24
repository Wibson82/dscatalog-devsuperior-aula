package com.devsuperior.dscatalog.services.exceptions;

import java.io.Serial;

public class DataBaseException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public DataBaseException(String msg){
        super(msg);
    }

    public DataBaseException(String msg, Throwable cause){
        super(msg, cause);
    }
}
