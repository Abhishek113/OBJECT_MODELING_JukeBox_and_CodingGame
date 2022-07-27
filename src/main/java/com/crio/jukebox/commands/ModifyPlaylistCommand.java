package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.dtos.ModifyPlaylistDto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserService;

public class ModifyPlaylistCommand implements ICommand{

    private final IUserService userService;

    public ModifyPlaylistCommand(IUserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String action = tokens.get(1);
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        List<String> songIds = tokens.subList(4, tokens.size());

        try{
            Playlist playlist = userService.modifyPlayList(action, userId, playlistId, songIds);
            ModifyPlaylistDto modifyPlaylistDto = new ModifyPlaylistDto(playlist);
            System.out.println(modifyPlaylistDto);
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
        catch(SongNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}   
