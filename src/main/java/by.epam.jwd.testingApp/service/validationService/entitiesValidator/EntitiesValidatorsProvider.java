package by.epam.jwd.testingApp.service.validationService.entitiesValidator;

import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.service.validationService.componentValidator.AbstractStringValidator;
import by.epam.jwd.testingApp.service.validationService.componentValidator.EmailValidator;
import by.epam.jwd.testingApp.service.validationService.componentValidator.NameValidator;
import by.epam.jwd.testingApp.service.validationService.componentValidator.PasswordValidator;

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
