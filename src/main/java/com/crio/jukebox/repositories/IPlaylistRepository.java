package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlaylistRepository extends CRUDRepository<Playlist, String>{

    public Optional<Playlist> findByName(String playlistName);
    public Playlist addSongsToPlaylist(Playlist playlist, List<Song> songs);
    public Playlist removeSongsFromPlaylist(Playlist playlist, List<Song> songs);
}