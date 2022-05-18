package com.crio.jukebox.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.exceptions.InvalidOperationException;

public class UserPlaylistManager {

    private Map<Playlist, List<Song>> userPlaylistSongsMap;
    private Playlist activePlaylist;
    public UserPlaylistManager()
    {
        userPlaylistSongsMap = new HashMap<>();

    }

    public UserPlaylistManager(Map<Playlist, List<Song>> userPlaylistSongsMap)
    {
        this.userPlaylistSongsMap = userPlaylistSongsMap;
    }

    public void addUserPlaylistSongs(Playlist playlist, List<Song> songs)
    {
        userPlaylistSongsMap.putIfAbsent(playlist, songs);
    }
    public void addSongsToPlaylist(Playlist playlist, List<Song> songs)
    {
        userPlaylistSongsMap.put(playlist, Stream.concat(userPlaylistSongsMap.get(playlist).stream(), songs.stream()).
                                                        collect(Collectors.toList()));
                                                        
        // TODO: update respective playlist object as well
    }
    
    public void removePlaylistFromMap(User user, Playlist playlist) throws InvalidOperationException
    {
        if(!userPlaylistSongsMap.containsKey(playlist))
            throw new InvalidOperationException("Playlist not found in user playlist songs!");
        
        userPlaylistSongsMap.remove(playlist);
        
    }
    
    public void setActivetPlaylist(Playlist playlist)
    {
        this.activePlaylist = playlist;
    }

    public Optional<Playlist> getActivePlaylist()
    {
        return Optional.ofNullable(this.activePlaylist);
    }
}