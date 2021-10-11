package by.epam.jwd.testingApp.controller.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandAccessLevel {

    private Map<CommandName,Integer> commands;
    private static CommandAccessLevel instance;

    public static final Integer NO_ROLE_REQUIRED = null;
    public static final Integer USER_ROLE_REQUIRED = 1;
    public static final Integer TUTOR_ROLE_REQUIRED = 2;

    public CommandAccessLevel(){
        commands = new HashMap<>();
        commands.put(CommandName.PAGE,NO_ROLE_REQUIRED);
        commands.put(CommandName.AUTHORIZATION,NO_ROLE_REQUIRED);
        commands.put(CommandName.REGISTRATION,NO_ROLE_REQUIRED);
        commands.put(CommandName.LOG_OUT,USER_ROLE_REQUIRED);
        commands.put(CommandName.TAKE_TEST,USER_ROLE_REQUIRED);
        commands.put(CommandName.EDIT_TEST,TUTOR_ROLE_REQUIRED);
        commands.put(CommandName.CREATE_TEST,TUTOR_ROLE_REQUIRED);
        commands.put(CommandName.DELETE_TEST,TUTOR_ROLE_REQUIRED);
        commands.put(CommandName.VIEW_RESULTS,USER_ROLE_REQUIRED);
        commands.put(CommandName.ADD_COMMENT,USER_ROLE_REQUIRED);
        commands.put(CommandName.ADD_QUESTION,TUTOR_ROLE_REQUIRED);
        commands.put(CommandName.EDIT_QUESTION,TUTOR_ROLE_REQUIRED);
        commands.put(CommandName.DELETE_QUESTION,TUTOR_ROLE_REQUIRED);
        commands.put(CommandName.VIEW_MY_TESTS,TUTOR_ROLE_REQUIRED);
        commands.put(CommandName.DELETE_STATEMENT,TUTOR_ROLE_REQUIRED);
    }

    public static CommandAccessLevel getInstance() {
        CommandAccessLevel localInstance = instance;
        if (localInstance == null) {
            synchronized (CommandAccessLevel.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CommandAccessLevel();
                }
            }
        }
        return localInstance;
    }

    public Integer getAccessLvlByCommandName(String commandName){
        if(commandName.equals("")) return commands.get(CommandName.PAGE) ;
        return commands.get(CommandName.valueOf(commandName.toUpperCase()));
    }

    public boolean isEnoughRight(String commandName,Integer accessLvl){
        if(commandName == null) return false;
        Integer CommandAccessLvl = getAccessLvlByCommandName(commandName);
        if(CommandAccessLvl == null) return true;
        if(accessLvl == null) return false;
        return CommandAccessLvl <= accessLvl;

    }

}
