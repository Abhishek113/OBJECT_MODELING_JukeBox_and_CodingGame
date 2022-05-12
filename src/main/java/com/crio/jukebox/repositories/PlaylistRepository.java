package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import java.util.stream.Collectors;

public class PlaylistRepository implements IPlaylistRepository{
    
    private Map<String ,Playlist> playlistMap;
    private Integer autoIncrement;

    public PlaylistRepository()
    {
        this.playlistMap = new HashMap<>();
    }

    public PlaylistRepository(Map<String ,Playlist> playlistMap)
    {
        this.playlistMap = playlistMap;
    }

    @Override
    public Optional<Playlist> findByName(String playlistName) {
        return playlistMap.entrySet().stream().filter(p ->p.getValue().getName().equals(playlistName)).map(Map.Entry::getValue).findFirst();
    }

    @Override
    public List<Playlist> findAll() {
        return playlistMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Playlist save(Playlist playlist) {
        if(playlist.getId() == null)
        {
            autoIncrement++;
            playlist = new Playlist(Integer.toString(autoIncrement), playlist.getName(), playlist.getSongs(), playlist.getCreator());
        }
        
        playlistMap.put(playlist.getId(), playlist);

        return playlist;
    }

    @Override
    public boolean existsById(String id) {
        if(playlistMap.containsKey(id))
            return true;
        return false;
    }

    @Override
    public Optional<Playlist> findById(String id) {
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public void deleteById(String id) {
        if(existsById(id))
            playlistMap.remove(id);
    }

    @Override
    public void delete(Playlist entity) {
        deleteById(entity.getId());
    }

    @Override
    public void addSongToPlaylist(Playlist playlist, Song song) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeSongFromPlaylist(Playlist playlist, Song song) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        return playlistMap.size();
    }
}   