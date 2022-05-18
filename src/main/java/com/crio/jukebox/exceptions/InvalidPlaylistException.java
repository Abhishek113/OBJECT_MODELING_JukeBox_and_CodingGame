package com.crio.jukebox.exceptions;

@SuppressWarnings("serial")
public class InvalidPlaylistException extends RuntimeException{
    
    public InvalidPlaylistException()
    {
        super();
    }

    public InvalidPlaylistException(String msg)
    {
        super(msg);
    }
}