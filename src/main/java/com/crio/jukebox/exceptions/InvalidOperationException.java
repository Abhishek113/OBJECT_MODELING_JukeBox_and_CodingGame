package com.crio.jukebox.exceptions;

@SuppressWarnings("serial")
public class InvalidOperationException extends RuntimeException{
    
    public InvalidOperationException()
    {
        super();
    }

    public InvalidOperationException(String msg)
    {
        super(msg);
    }
}