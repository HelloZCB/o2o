package com.zcb.o2o.exceptions;

public class ProductOperationException extends RuntimeException {

    private static final long serialVersionUID = -787769794869742089L;

    public ProductOperationException(String msg) {
        super(msg);
    }
}
