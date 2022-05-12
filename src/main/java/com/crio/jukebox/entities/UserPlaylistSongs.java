package com.crio.jukebox.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserPlaylistSongs {

    private Map<Playlist, List<Song>> userPlaylistSongsMap;

    public UserPlaylistSongs()
    {
        userPlaylistSongsMap = new HashMap<>();
    }

    public UserPlaylistSongs(Map<Playlist, List<Song>> userPlaylistSongsMap)
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
    
}