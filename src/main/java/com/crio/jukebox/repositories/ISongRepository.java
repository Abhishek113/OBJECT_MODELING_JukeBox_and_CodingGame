package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;

import com.crio.jukebox.entities.Song;

public interface ISongRepository extends CRUDRepository<Song, String> {

    public Optional<Song> findByName(String songName);
    public List<Song> getAllRequestedSongsbyIds(List<String> songIds);
}