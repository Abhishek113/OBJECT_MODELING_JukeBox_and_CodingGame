package com.crio.jukebox.commands;

import java.io.FileNotFoundException;
import java.util.List;

import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.SongService;

public class LoadDataCommand implements ICommand{
    
    private final ISongService songService;

    public LoadDataCommand(ISongService songService)
    {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens){
        String csvPath = tokens.get(1);
        try
        {
            songService.loadSongsIntoSongRepository(csvPath);
            System.out.println("Songs Loaded successfully");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Given CSV file not found");
        }
        
    }
}
