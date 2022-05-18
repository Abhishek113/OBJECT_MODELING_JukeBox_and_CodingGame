package com.crio.jukebox.services;

import java.util.List;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.UserNotFoundException;

public interface IUserService {
    
    public User create(String name);
    public Playlist createPlaylist(String userId, String PLaylistName, List<String> songIds) throws UserNotFoundException;
    public void deletePlaylist(String userId, String playlistId) throws UserNotFoundException;
    public void modifyPlayList(String action, String userId, String playlistId, List<String> songIds) throws UserNotFoundException;
    public void playPlaylist(String userId, String playlistId) throws UserNotFoundException;
    public void playSong(String userId, String option) throws UserNotFoundException;
    public User getValidUser(final String userId) throws UserNotFoundException;
}