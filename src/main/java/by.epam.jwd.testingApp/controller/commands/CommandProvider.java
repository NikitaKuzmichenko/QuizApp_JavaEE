package by.epam.jwd.testingApp.controller.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName,Command> commands;

    public CommandProvider(){
        commands = new HashMap<>();
        commands.put(CommandName.NO_COMMAND,null);
        // add some commands
    }

    public Command selectCommand(String commandName){
        if(commands==null) return null;
        if(commandName==null) return commands.get(CommandName.NO_COMMAND) ;
        return commands.get(CommandName.valueOf(commandName.toUpperCase()));
    }
}
