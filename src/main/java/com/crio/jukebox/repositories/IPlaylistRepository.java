package com.crio.jukebox.repositories;

import java.util.Optional;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlaylistRepository extends CRUDRepository<Playlist, String>{

    public Optional<Playlist> findByName(String playlistName);
    public void addSongToPlaylist(Playlist playlist, Song song);
    public void removeSongFromPlaylist(Playlist playlist, Song song);
}