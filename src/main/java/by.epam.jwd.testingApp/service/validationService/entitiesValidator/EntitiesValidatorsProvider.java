package by.epam.jwd.testingApp.service.validationService.entitiesValidator;

import by.epam.jwd.testingApp.entity.User;

public class EntitiesValidatorsProvider {

    private static AbstractEntitiesValidator<User> userValidator;

    private EntitiesValidatorsProvider(){
        userValidator = new UserValidator();
    }
    private static class SingletonHolder {
        public static final EntitiesValidatorsProvider HOLDER_INSTANCE = new EntitiesValidatorsProvider();
    }

    public static EntitiesValidatorsProvider getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public AbstractEntitiesValidator<User> getUserValidator() {
        return userValidator;
    }
}
