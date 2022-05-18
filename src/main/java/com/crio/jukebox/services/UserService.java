package com.crio.jukebox.services;

import java.util.List;
import java.util.Optional;

import com.crio.jukebox.entities.PlaySongSwitches;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.InvalidPlaylistException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ISongRepository songRepository;
    private final IPlaylistRepository playlistRepository;
    private final IPlayListService playListService;

    public UserService(IUserRepository userRepository, ISongRepository songRepository, IPlaylistRepository playlistRepository, IPlayListService playListService)
    {   
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
        this.playListService = playListService;
    }

    @Override
    public User create(String name) {
        User user = new User(name);
        user = userRepository.save(user);
        return null;
    }

    @Override
    public Playlist createPlaylist(String userId, String playlistName, List<String> songIds) throws UserNotFoundException{

        List<Song> allRequestedSongs = songRepository.getAllRequestedSongsbyIds(songIds);
        User user = getValidUser(userId);

        Playlist playlist = playListService.createPlaylist(user, playlistName, allRequestedSongs);
        user.addPlaylist(playlist);
        userRepository.save(user);
        return playlist;
    }


    @Override
    public void deletePlaylist(String userId, String playlistId) throws UserNotFoundException, PlayListNotFoundException, InvalidPlaylistException{
        
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(()->new PlayListNotFoundException("Plpaylist with id : " + playlistId + " not found !"));
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with id : " + userId + " not found !"));
        validatePlaylist(playlist, userId);
        playlistRepository.delete(playlist);
        user.deletePlaylist(playlist);
        userRepository.save(user);
        
    }

    @Override
    public void modifyPlayList(String action, String userId, String playlistId, List<String> songIds) throws PlayListNotFoundException, UserNotFoundException, InvalidOperationException, SongNotFoundException{
        // TODO Auto-generated method stub
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(()->new PlayListNotFoundException("Plpaylist with id : " + playlistId + " not found !"));
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with id : " + userId + " not found !"));
        validatePlaylist(playlist, userId);
        List<Song> allRequestedSongs = songRepository.getAllRequestedSongsbyIds(songIds);

        playListService.modifyPlayList(action, playlist, allRequestedSongs);

    }

    @Override
    public void playPlaylist(String userId, String playlistId) throws PlayListNotFoundException, UserNotFoundException, InvalidOperationException{

        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(()->new PlayListNotFoundException("Plpaylist with id : " + playlistId + " not found !"));
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with id : " + userId + " not found !"));
        validatePlaylist(playlist, userId);
        user.setActivePlaylist(playlist);
        userRepository.save(user);
        Song song = playListService.playPlaylist(playlist);

    }

    @Override
    public void playSong(String userId, String option) throws EmptyPlaylistException{
        
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with id : " + userId + " not found !"));
        Playlist playlist = user.getActivePlaylist().orElseThrow(()->new RuntimeException("You have no active playlist"));

        validatePlaylist(playlist, userId);
        if(option.equals(PlaySongSwitches.BACK.toString()))
        {
            Song song = playListService.playlBackSong(playlist);
        }
        
        else if(option.equals(PlaySongSwitches.NEXT.toString()))
        {
            Song song = playListService.playNextSong(playlist);
        }
        else
        {
            Song song = playListService.playSongById(playlist, option);
        }


    }

    @Override
    public User getValidUser(final String userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("User with id: " + userId + "not found !");
        }

        return user.get();
    }

    public void validatePlaylist(final Playlist playlist, final String creatorId) throws UserNotFoundException, InvalidPlaylistException
    {
        if(!userRepository.existsById(creatorId))
            throw new UserNotFoundException("User does not found !");
        
        if(!creatorId.equals(playlist.getCreator().getId()))
            throw new InvalidPlaylistException("Playlist can not be deleted as user with user id : " + creatorId + " is not the creator of the playlist with id : " + playlist.getId());
        
        //TODO: optional -> raise InvalidOperation if playlist is running.
    }
    
}