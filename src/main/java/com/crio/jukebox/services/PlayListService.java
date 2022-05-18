package com.crio.jukebox.services;

import java.util.List;
import java.util.Optional;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.PlaylistModificationActions;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
// import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.PlaylistRepository;

public class PlayListService implements IPlayListService {

    private PlaylistRepository playlistRepository;

    public PlayListService(PlaylistRepository playlistRepository)
    {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist createPlaylist(User user, String playlistName, List<Song> songs)
    {
        Playlist playlist = new Playlist(playlistName, songs, user);
        playlist = playlistRepository.save(playlist);
        return playlist;
    }

    @Override
    public void deletePlaylist(String playlistId) throws PlayListNotFoundException{
        
        // Playlist playlist = getValidPlaylist(playlistId);
        // playlistRepository.delete(playlist);
        
    }

    @Override
    public Playlist modifyPlayList(String action, Playlist playlist, List<Song> songs)
            throws PlayListNotFoundException, InvalidOperationException {
        
        if(action.equals(PlaylistModificationActions.getValueOfAction(PlaylistModificationActions.ADD_SONG)))
            return playlistRepository.addSongsToPlaylist(playlist, songs);
        
        if(action.equals(PlaylistModificationActions.getValueOfAction(PlaylistModificationActions.DELETE_SONG)))
            return playlistRepository.removeSongsFromPlaylist(playlist, songs);
        
        throw new InvalidOperationException(action + " is not a playlist modification action");
        
    }

    @Override
    public Song playPlaylist(Playlist playlist) throws EmptyPlaylistException {
        
        Song song =  playlist.play();
        playlistRepository.save(playlist);
        return song;
    }

    @Override
    public Song playlBackSong(Playlist playlist) throws EmptyPlaylistException{
        
        return playlist.switchToBackSong();
    }

    @Override
    public Song playNextSong(Playlist playlist) throws EmptyPlaylistException {

        return playlist.switchToNextSong();
    }

    @Override
    public Song playSongById(Playlist playlist, String songId) throws EmptyPlaylistException, SongNotFoundException {
        
        return playlist.swtichToSongById(songId);
    }

    @Override
    public Playlist getValidPlaylist(String playlistId) throws PlayListNotFoundException {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);

        if(!playlist.isPresent())
        {
            throw new PlayListNotFoundException("Playlist wioth id : " + playlistId + " not found !");
        
        }

        return playlist.get();
    }
    
    
}