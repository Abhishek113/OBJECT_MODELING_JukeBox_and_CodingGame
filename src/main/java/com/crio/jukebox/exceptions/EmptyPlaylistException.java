package com.crio.jukebox.exceptions;

@SuppressWarnings("serial")
public class EmptyPlaylistException extends RuntimeException{
    
    public EmptyPlaylistException()
    {
        super();
    }

    public EmptyPlaylistException(String msg)
    {
        super(msg);
    }
}