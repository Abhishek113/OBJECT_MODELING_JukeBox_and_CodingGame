package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.crio.jukebox.exceptions.InvalidCommand;

public class CommandInvoker{

    private Map<String, ICommand> commands;

    public CommandInvoker()
    {
        commands = new HashMap<>();
    }

    public void registerCommand(String commadName, ICommand commandObject)
    {
        if(commadName != null)
            commands.put(commadName, commandObject);
        else
            throw new InvalidCommand("Invalid Command");
    }

    public Optional<ICommand> getCommand(String command)
    {
        return Optional.ofNullable(commands.get(command));
    }

    public void invokeCommand(String commandName, List<String> tokens) throws InvalidCommand
    {
        ICommand command = getCommand(commandName).orElseThrow(() -> new InvalidCommand("Command not found!"));
        command.execute(tokens);
    }
    
}
