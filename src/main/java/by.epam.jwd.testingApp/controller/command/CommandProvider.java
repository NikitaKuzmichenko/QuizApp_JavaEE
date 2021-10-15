package by.epam.jwd.testingApp.controller.command;

import by.epam.jwd.testingApp.controller.command.commandImpl.AddQuestion;
import by.epam.jwd.testingApp.controller.command.commandImpl.Authorization;
import by.epam.jwd.testingApp.controller.command.commandImpl.ChangeStatus;
import by.epam.jwd.testingApp.controller.command.commandImpl.CreateTest;
import by.epam.jwd.testingApp.controller.command.commandImpl.DeleteQuestion;
import by.epam.jwd.testingApp.controller.command.commandImpl.DeleteStatement;
import by.epam.jwd.testingApp.controller.command.commandImpl.DeleteTest;
import by.epam.jwd.testingApp.controller.command.commandImpl.EditQuestion;
import by.epam.jwd.testingApp.controller.command.commandImpl.EditTest;
import by.epam.jwd.testingApp.controller.command.commandImpl.FinishTest;
import by.epam.jwd.testingApp.controller.command.commandImpl.GetQuestion;
import by.epam.jwd.testingApp.controller.command.commandImpl.LogOut;
import by.epam.jwd.testingApp.controller.command.commandImpl.Registration;
import by.epam.jwd.testingApp.controller.command.commandImpl.StartTest;
import by.epam.jwd.testingApp.controller.command.commandImpl.ToPage;
import by.epam.jwd.testingApp.controller.command.commandImpl.ViewMyTests;
import by.epam.jwd.testingApp.controller.command.commandImpl.ViewResults;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static Map<CommandName,Command> commands;

    private CommandProvider(){
        commands = new HashMap<>();

        commands.put(CommandName.PAGE,new ToPage());
        commands.put(CommandName.AUTHORIZATION,new Authorization());
        commands.put(CommandName.REGISTRATION,new Registration());
        commands.put(CommandName.LOG_OUT,new LogOut());
        commands.put(CommandName.TAKE_TEST,new StartTest());
        commands.put(CommandName.FINISH_TEST,new FinishTest());
        commands.put(CommandName.GET_QUESTION,new GetQuestion());
        commands.put(CommandName.CREATE_TEST,new CreateTest());
        commands.put(CommandName.EDIT_TEST,new EditTest());
        commands.put(CommandName.DELETE_TEST,new DeleteTest());
        commands.put(CommandName.ADD_QUESTION,new AddQuestion());
        commands.put(CommandName.EDIT_QUESTION,new EditQuestion());
        commands.put(CommandName.DELETE_QUESTION,new DeleteQuestion());
        commands.put(CommandName.VIEW_RESULTS,new ViewResults());
        commands.put(CommandName.VIEW_MY_TESTS,new ViewMyTests());
        commands.put(CommandName.DELETE_STATEMENT,new DeleteStatement());
        commands.put(CommandName.CHANGE_STATUS,new ChangeStatus());
    }

    private static class SingletonHolder {
        public static final CommandProvider HOLDER_INSTANCE = new CommandProvider();
    }

    public static CommandProvider getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public Command selectCommand(String commandName){
        if(commandName.equals("")) return commands.get(CommandName.PAGE);
        return commands.get(CommandName.valueOf(commandName.toUpperCase()));
    }
}
