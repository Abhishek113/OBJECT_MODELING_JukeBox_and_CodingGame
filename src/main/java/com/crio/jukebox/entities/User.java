package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User extends BaseEntity {

    private String name;
    private List<Playlist> userPlaylists;
    private UserPlaylistManager userPlaylistManager;
    // private Playlist activeUserPlaylist;

    public User(User user)
    {
        this(user.getId(), user.getName(), user.getAllPlaylists(), user.getUserPlaylistSongs());
    }

    public User(String name)
    {
        //this.id = id;
        this.name = name;
        userPlaylists = new ArrayList<>();
        userPlaylistManager = new UserPlaylistManager();
    }

    public User(String id, String name, List<Playlist> userPlaylists, UserPlaylistManager userPlaylistManager)
    {
        this(name, userPlaylists, userPlaylistManager);
        this.id = id;
    }

    public User(String name, List<Playlist> userPlaylists, UserPlaylistManager userPlaylistManager)
    {
        this(name);
        this.userPlaylists = userPlaylists;
        this.userPlaylistManager = userPlaylistManager;
    }

    public String getName()
    {
        return this.name;
    }

    public List<Playlist> getAllPlaylists()
    {
        return this.userPlaylists;
    }

    public UserPlaylistManager getUserPlaylistSongs()
    {
        return this.userPlaylistManager;
    }

    public void setActivePlaylist(Playlist playlist)
    {
        userPlaylistManager.setActivetPlaylist(playlist);
    }

    public Optional<Playlist> getActivePlaylist()
    {

        return userPlaylistManager.getActivePlaylist();
    }

    public void addPlaylist(Playlist playlist)
    {
        this.userPlaylists.add(playlist);
        addPlaylistSongs(playlist, playlist.getSongs());
    }

    public void addPlaylistSongs(Playlist playlist, List<Song> songs)
    {
        userPlaylistManager.addUserPlaylistSongs(playlist, songs);
    }

    public void deletePlaylist(Playlist playlist)
    {
        userPlaylists.removeIf(p->p.getId().equals(playlist.getId()));
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