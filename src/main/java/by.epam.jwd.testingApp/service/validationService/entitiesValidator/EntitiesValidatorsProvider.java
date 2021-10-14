package by.epam.jwd.testingApp.service.validationService.entitiesValidator;

import by.epam.jwd.testingApp.entity.User;

public class EntitiesValidatorsProvider {

    private static EntitiesValidatorsProvider instance;

    private final AbstractEntitiesValidator<User> userValidator;

    private EntitiesValidatorsProvider(){
        userValidator = new UserValidator();
    }
    public static EntitiesValidatorsProvider newInstance() {
        EntitiesValidatorsProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (EntitiesValidatorsProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EntitiesValidatorsProvider();
                }
            }
        }
        return localInstance;
    }

    public AbstractEntitiesValidator<User> getUserValidator() {
        return userValidator;
    }
}
