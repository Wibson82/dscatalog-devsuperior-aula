package com.devsuperior.dscatalog.resources.exceptions;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    @Serial
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> erros = new ArrayList<>();

    /*public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }*/

    public List<FieldMessage> getErrors() {
        return erros;
    }

    public void addError(String fieldName, String message) {
       erros.add(new FieldMessage(fieldName, message));
    }
}
