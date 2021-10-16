package by.epam.jwd.testingApp.service.validationService.entitiesValidator;

import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.entity.User;

public class EntitiesValidatorsProvider {

    private static AbstractEntitiesValidator<User> userValidator;
    private static AbstractEntitiesValidator<Question> questionValidator;

    private EntitiesValidatorsProvider(){
        userValidator = new UserValidator();
        questionValidator = new QuestionValidator();
    }

    private static class SingletonHolder {
        public static final EntitiesValidatorsProvider HOLDER_INSTANCE = new EntitiesValidatorsProvider();
    }

    public  AbstractEntitiesValidator<Question> getQuestionValidator() {
        return questionValidator;
    }

    public AbstractEntitiesValidator<User> getUserValidator() {
        return userValidator;
    }

    public static EntitiesValidatorsProvider getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

}
