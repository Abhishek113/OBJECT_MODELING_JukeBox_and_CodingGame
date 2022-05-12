package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity {
    
    private final String name;
    private final Duration duration;
    private final SongGenre genre;
    private final List<String> artists;
    private final String owner;

    public Song(Song song)
    {
        this(song.getId(), song.getName(), song.getDuration(), song.getGenre(), song.getAllArtists(), song.getOwner());
    }

    public Song(String id, String name, Duration duration, SongGenre genre, List<String> artists, String owner)
    {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.genre = genre;
        this.artists = artists;
        this.owner = owner;
    }

    public String getName()
    {
        return name;
    }

    public Duration getDuration()
    {
        return duration;
    }

    public SongGenre getGenre()
    {
        return genre;
    }

    public List<String> getAllArtists()
    {
        return artists;
    }

    public String getOwner()
    {
        return owner;
    }
    
}