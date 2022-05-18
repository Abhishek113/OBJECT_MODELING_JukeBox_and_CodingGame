package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.SongNotFoundException;

import java.util.stream.Collectors;

public class SongRepository implements ISongRepository{
    
    private Map<String, Song> songMap;
    private Integer autoIncrement;
    
    public SongRepository()
    {
        this.songMap = new HashMap<>();
    }

    public SongRepository(Map<String, Song> songMap)
    {
        this.songMap = songMap;
    }
    
    @Override
    public Optional<Song> findByName(String songName) {
        return songMap.entrySet().stream().filter(song ->song.getValue().getName().equals(songName))
                                                            .map(Map.Entry::getValue).findFirst();
    }

    @Override
    public Song save(Song song) {
        if(song.getId() == null)
        {
            autoIncrement++;
            song = new Song(Integer.toString(autoIncrement), song.getName(), song.getDuration(), song.getGenre(), song.getAllArtists(), song.getOwner());
        }
        songMap.put(song.getId(), song);
        return song;
    }

    @Override
    public List<Song> findAll() {

        if(songMap.size() == 0)
            return new ArrayList<>();

        return songMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public List<Song> getAllRequestedSongsbyIds(List<String> songIds) throws SongNotFoundException
    {
        List<Song> allRequestedSongs = new ArrayList<>();
        for(String id: songIds)
        {
            Optional<Song> song = findById(id);
            if(song.isPresent())
                allRequestedSongs.add(song.get());
            else
            {
                String expMsg = "Song of this id: " + id + " not found in repository !";
                throw new SongNotFoundException(expMsg);
                // System.out.println(expMsg);
            }
        }

        return allRequestedSongs;
    }

    @Override
    public boolean existsById(String id) {
        
        if(songMap.containsKey(id))
            return true;
        return false;
    }

    @Override
    public void deleteById(String id) {
        if(existsById(id))
            songMap.remove(id);
    }

    @Override
    public void delete(Song entity) {
        deleteById(entity.getId());
    }

    @Override
    public long count() {
        return songMap.size();
    }

}