package by.epam.jwd.testingApp.controller.commands;

import by.epam.jwd.testingApp.controller.commands.commandImpl.Authorization;
import by.epam.jwd.testingApp.controller.commands.commandImpl.ChangeTest;
import by.epam.jwd.testingApp.controller.commands.commandImpl.CreateTest;
import by.epam.jwd.testingApp.controller.commands.commandImpl.LogOut;
import by.epam.jwd.testingApp.controller.commands.commandImpl.Registration;
import by.epam.jwd.testingApp.controller.commands.commandImpl.RemoveTest;
import by.epam.jwd.testingApp.controller.commands.commandImpl.TakeTest;
import by.epam.jwd.testingApp.controller.commands.commandImpl.ToPage;
import by.epam.jwd.testingApp.controller.commands.commandImpl.ViewResults;
import by.epam.jwd.testingApp.controller.commands.commandImpl.WrongRole;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName,Command> commands;

    public CommandProvider(){
        commands = new HashMap<>();
        commands.put(CommandName.PAGE,new ToPage());
        commands.put(CommandName.AUTHORIZATION,new Authorization());
        commands.put(CommandName.REGISTRATION,new Registration());
        commands.put(CommandName.LOG_OUT,new LogOut());
        commands.put(CommandName.TEST,new TakeTest());
        commands.put(CommandName.CHANGE_TEST,new ChangeTest());
        commands.put(CommandName.CREATE_TEST,new CreateTest());
        commands.put(CommandName.REMOVE_TEST,new RemoveTest());
        commands.put(CommandName.VIEW_RESULTS,new ViewResults());
        commands.put(CommandName.WRONG_ROLE,new WrongRole());
    }

    public Command selectCommand(String commandName){
        if(commandName.equals("")) return commands.get(CommandName.PAGE) ;
        return commands.get(CommandName.valueOf(commandName.toUpperCase()));
    }
}
