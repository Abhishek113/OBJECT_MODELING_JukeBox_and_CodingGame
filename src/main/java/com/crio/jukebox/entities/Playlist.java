package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist extends BaseEntity {

    private final String name;
    private List<Song> songs;
    private final User creator;

    public Playlist(Playlist playlist)
    {
        this(playlist.getId(), playlist.getName(), playlist.getSongs(), playlist.getCreator());
    }

    public Playlist(String id, String name, List<Song> songs, User creator)
    {
        this.id = id;
        this.name = name;
        this.songs = songs;
        this.creator = creator;
    }

    public Playlist(String id, String name, User creator)
    {
        this.id = id;
        this.name = name;
        this.songs = new ArrayList<>();
        this.creator = creator;
    }

    public String getName()
    {
        return name;
    }

    public List<Song> getSongs()
    {
        return songs;
    }

    public User getCreator()
    {
        return creator;
    }

    public void addSong(Song song)
    {
        this.songs.add(song);
    }

    public void removeSong(Song song)
    {
        //TODO:
    }

    
    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(getClass() != obj.getClass())
            return false;
        
        Song song = (Song)obj;

        return this.equals(song);
        
    }

    @Override
    public String toString()
    {
        return "Playlist [id = " + id + ", name = " + name + ", creator = " + creator + "]";
    }
    
}