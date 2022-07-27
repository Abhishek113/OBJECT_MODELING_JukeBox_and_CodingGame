package com.crio.jukebox.commands;

import java.util.List;

import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.dtos.PlayPlaylistDto;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserService;

public class PlaySongCommand implements ICommand{
    
    private final IUserService userService;

    public PlaySongCommand(IUserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String choice = tokens.get(2);

        try{
            Song song = userService.playSong(userId, choice);
            PlayPlaylistDto playPlaylistDto = new PlayPlaylistDto(song)
;            System.out.println(playPlaylistDto);
        }
        catch(EmptyPlaylistException e)
        {
            System.out.println(e.getMessage());
        }
        catch(SongNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(UserNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
