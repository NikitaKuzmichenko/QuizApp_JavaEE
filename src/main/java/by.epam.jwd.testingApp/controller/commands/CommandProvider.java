package by.epam.jwd.testingApp.controller.commands;

import by.epam.jwd.testingApp.controller.commands.commandImpl.ToWelcomePage;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName,Command> commands;

    public CommandProvider(){
        commands = new HashMap<>();
        commands.put(CommandName.TO_WELCOME_PAGE,new ToWelcomePage());
        // add some commands
    }

    public Command selectCommand(String commandName){
        if(commands==null) return null;
        if(commandName==null) return commands.get(CommandName.TO_WELCOME_PAGE) ;
        return commands.get(CommandName.valueOf(commandName.toUpperCase()));
    }
}
