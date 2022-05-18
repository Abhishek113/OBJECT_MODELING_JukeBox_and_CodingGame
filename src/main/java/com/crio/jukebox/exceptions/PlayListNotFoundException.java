package com.crio.jukebox.exceptions;

@SuppressWarnings("serial")
public class PlayListNotFoundException extends RuntimeException{
    
    public PlayListNotFoundException()
    {
        super();
    }

    public PlayListNotFoundException(String msg)
    {
        super(msg);
    }
}