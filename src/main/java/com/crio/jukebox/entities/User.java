package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity {

    private String name;
    private List<Playlist> userPlaylists;
    private UserPlaylistSongs userPlaylistSongs;


    public User(User user)
    {
        this(user.getId(), user.getName(), user.getAllPlaylists(), user.getUserPlaylistSongs());
    }

    public User(String id, String name)
    {
        this.id = id;
        this.name = name;
        userPlaylists = new ArrayList<>();
        userPlaylistSongs = new UserPlaylistSongs();
    }

    public User(String id, String name, List<Playlist> userPlaylists, UserPlaylistSongs userPlaylistSongs)
    {
        this(id, name);
        this.userPlaylists = userPlaylists;
        this.userPlaylistSongs = userPlaylistSongs;
    }

    public String getName()
    {
        return this.name;
    }

    public List<Playlist> getAllPlaylists()
    {
        return this.userPlaylists;
    }

    public UserPlaylistSongs getUserPlaylistSongs()
    {
        return this.userPlaylistSongs;
    }

    public void addPlaylist()
    {
        //TODO: 
    }

    public void createPlaylist()
    {
        // TODO:
    }

    public void addSongToPlaylist()
    {
        //TODO:
    }

    public void removeSongFromPlaylist()
    {
        //TODO:
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", playlists=" + userPlaylists + "]";
    }
    
}