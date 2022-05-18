package com.crio.jukebox.services;

import java.util.List;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;

public interface IPlayListService {
    
    public Playlist createPlaylist(User user, String playlistName, List<Song> songs);
    public void deletePlaylist(String playlistId) throws PlayListNotFoundException;
    public Playlist modifyPlayList(String action, Playlist playlist, List<Song> songs) throws PlayListNotFoundException;
    public Song playPlaylist(Playlist playlist) throws EmptyPlaylistException;
    public Song playlBackSong(Playlist playlist) throws EmptyPlaylistException;
    public Song playNextSong(Playlist playlist) throws EmptyPlaylistException;
    public Song playSongById(Playlist playlist, String songId) throws EmptyPlaylistException, SongNotFoundException;
    public Playlist getValidPlaylist(String playlistId) throws PlayListNotFoundException;
    
}