package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.exceptions.InvalidPlaylistException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserService;

public class DeletePlaylistCommand implements ICommand{
    
    private final IUserService userSerive;

    public DeletePlaylistCommand(IUserService userService)
    {
        this.userSerive = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);

        try{
            userSerive.deletePlaylist(userId, playlistId);
            System.out.println("Delete Successful");
        
        }
        catch(UserNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(PlayListNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(InvalidPlaylistException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
