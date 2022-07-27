package com.crio.jukebox.commands;

import java.util.List;

import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.dtos.CreatePlaylistDto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.UserService;

public class CreatePlaylistCommand implements ICommand{
    
    private final IUserService userService;

    public CreatePlaylistCommand(IUserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playListName = tokens.get(2);
        List<String> songIds = tokens.subList(3, tokens.size());

        try{
            Playlist playlist = userService.createPlaylist(userId, playListName, songIds);
            CreatePlaylistDto createPlaylistDto = new CreatePlaylistDto(playlist);
            System.out.println(createPlaylistDto);
        }
        catch(UserNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(SongNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
}
