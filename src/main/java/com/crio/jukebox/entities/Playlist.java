package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class Playlist extends BaseEntity {

    private final String name;
    private List<Song> songs;
    private final User creator;
    private int currentlyPlayingSongNumber;

    public Playlist(Playlist playlist)
    {
        this(playlist.getId(), playlist.getName(), playlist.getSongs(), playlist.getCreator(), playlist.currentlyPlayingSongNumber());
    }

    public Playlist(String id, String name, List<Song> songs, User creator)
    {
        this(name, songs, creator);
        this.id = id;
        
    }

    public Playlist(String name, List<Song> songs, User creator)
    {
        
        this.name = name;
        this.songs = songs;
        this.creator = creator;
    }


    public Playlist(String id, String name, User creator)
    {
        this(name, creator);
        this.id = id;

    }

    public Playlist(String name, User creator)
    {
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

    public boolean isPlaylistEmpty()
    {
        return songs.isEmpty();
    }

    public int getcurrentlyPlayingSongNumber()
    {
        return this.currentlyPlayingSongNumber;
    }


    private int getSongIndex(Song song) throws SongNotFoundException
    {
        for(int i=0; i<this.songs.size(); ++i)
        {
            if(songs.get(i).equals(song))
                return i;
        }

        throw new SongNotFoundException("Song not found on playlist");

    }
    private void setCurrentlyPlayingSong(Song song) throws SongNotFoundException
    {
        this.currentlyPlayingSongNumber = getSongIndex(song);
    }

    public Song play() throws EmptyPlaylistException
    {
        if(this.isPlaylistEmpty())
            throw new EmptyPlaylistException("Playlist is empty");
        
        this.currentlyPlayingSongNumber = 0;
        return songs.get(0);
    }

    public Song switchToBackSong() throws EmptyPlaylistException
    {
        if(isPlaylistEmpty())
            throw new EmptyPlaylistException("Playlis with id : " + this.getId() + " is empty !");
        
        this.currentlyPlayingSongNumber = (this.currentlyPlayingSongNumber - 1 + this.songs.size()) % this.songs.size();

        return songs.get(this.currentlyPlayingSongNumber);
    }

    public Song switchToNextSong() throws EmptyPlaylistException
    {
        if(isPlaylistEmpty())
            throw new EmptyPlaylistException("Playlis with id : " + this.getId() + " is empty !");
        
        this.currentlyPlayingSongNumber = (this.currentlyPlayingSongNumber + 1) % songs.size();

        return songs.get(this.currentlyPlayingSongNumber);
    }

    private Optional<Song> getSongById(String songId)
    {
        return songs.stream().filter(s -> s.getId().equals(songId)).findFirst();
    }
    public Song swtichToSongById(String songId) throws SongNotFoundException, EmptyPlaylistException
    {
        if(isPlaylistEmpty())
            throw new EmptyPlaylistException("Playlis with id : " + this.getId() + " is empty !");

        Song song = getSongById(songId).orElseThrow(() -> new SongNotFoundException("Song with song id : " + songId + "not found!"));
        setCurrentlyPlayingSong(song);
        return song;
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