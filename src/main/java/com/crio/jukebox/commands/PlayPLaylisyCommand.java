package com.crio.jukebox.commands;

import java.util.List;

import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.dtos.PlayPlaylistDto;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserService;

public class PlayPLaylisyCommand implements ICommand{
    private final IUserService userService;

    public PlayPLaylisyCommand(IUserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);

        try
        {
            Song song  = userService.playPlaylist(userId, playlistId);
            PlayPlaylistDto playPlaylistDto = new PlayPlaylistDto(song);
            System.out.println(playPlaylistDto);
        }
        catch(PlayListNotFoundException e)
        {   
            System.out.println(e.getMessage());
        }
        catch(UserNotFoundException e)
        {   
            System.out.println(e.getMessage());
        }
        catch(InvalidOperationException e)
        {   
            System.out.println(e.getMessage());
        }
    }
}
