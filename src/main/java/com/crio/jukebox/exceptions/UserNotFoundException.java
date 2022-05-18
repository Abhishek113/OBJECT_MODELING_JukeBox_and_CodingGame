package com.crio.jukebox.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException()
    {
        super();
    }

    public UserNotFoundException(String msg)
    {
        super(msg);
    }
    
}