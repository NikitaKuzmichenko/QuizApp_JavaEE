package by.epam.jwd.testingApp.controller.commands;

import by.epam.jwd.testingApp.controller.commands.commandImpl.AddComment;
import by.epam.jwd.testingApp.controller.commands.commandImpl.AddQuestion;
import by.epam.jwd.testingApp.controller.commands.commandImpl.Authorization;
import by.epam.jwd.testingApp.controller.commands.commandImpl.CreateTest;
import by.epam.jwd.testingApp.controller.commands.commandImpl.DeleteQuestion;
import by.epam.jwd.testingApp.controller.commands.commandImpl.DeleteStatement;
import by.epam.jwd.testingApp.controller.commands.commandImpl.DeleteTest;
import by.epam.jwd.testingApp.controller.commands.commandImpl.EditQuestion;
import by.epam.jwd.testingApp.controller.commands.commandImpl.EditTest;
import by.epam.jwd.testingApp.controller.commands.commandImpl.LogOut;
import by.epam.jwd.testingApp.controller.commands.commandImpl.Registration;
import by.epam.jwd.testingApp.controller.commands.commandImpl.TakeTest;
import by.epam.jwd.testingApp.controller.commands.commandImpl.ToPage;
import by.epam.jwd.testingApp.controller.commands.commandImpl.ViewMyTests;
import by.epam.jwd.testingApp.controller.commands.commandImpl.ViewResults;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static Map<CommandName,Command> commands;
    private static CommandProvider instance;

    private CommandProvider(){
        commands = new HashMap<>();

        commands.put(CommandName.PAGE,new ToPage());
        commands.put(CommandName.AUTHORIZATION,new Authorization());
        commands.put(CommandName.REGISTRATION,new Registration());
        commands.put(CommandName.LOG_OUT,new LogOut());
        commands.put(CommandName.TAKE_TEST,new TakeTest());
        commands.put(CommandName.CREATE_TEST,new CreateTest());
        commands.put(CommandName.EDIT_TEST,new EditTest());
        commands.put(CommandName.DELETE_TEST,new DeleteTest());
        commands.put(CommandName.ADD_QUESTION,new AddQuestion());
        commands.put(CommandName.EDIT_QUESTION,new EditQuestion());
        commands.put(CommandName.DELETE_QUESTION,new DeleteQuestion());
        commands.put(CommandName.VIEW_RESULTS,new ViewResults());
        commands.put(CommandName.VIEW_MY_TESTS,new ViewMyTests());
        commands.put(CommandName.ADD_COMMENT,new AddComment());
        commands.put(CommandName.DELETE_STATEMENT,new DeleteStatement());
    }

    public static CommandProvider getInstance() {
        CommandProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (CommandProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CommandProvider();
                }
            }
        }
        return localInstance;
    }

    public Command selectCommand(String commandName){
        if(commandName.equals("")) return commands.get(CommandName.PAGE) ;
        return commands.get(CommandName.valueOf(commandName.toUpperCase()));
    }
}
