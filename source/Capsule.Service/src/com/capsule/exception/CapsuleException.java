package com.capsule.exception;


public class CapsuleException extends Exception {

    private static final long serialVersionUID=1L;

    public CapsuleException(String message){
        super(message);
    }
    
    public CapsuleException(Throwable cause){
        super(cause);
    }
}
