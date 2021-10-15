package by.epam.jwd.testingApp.service.validationService.componentValidator;

import by.epam.jwd.testingApp.service.testSortingService.TestsSorterProvider;

public class ComponentValidatorsProvider {

    private static ComponentValidatorsProvider instance;

    private final AbstractStringValidator emailValidator;
    private final AbstractStringValidator passwordValidator;
    private final AbstractStringValidator nameValidator;

    private ComponentValidatorsProvider(){
        emailValidator = new EmailValidator();
        passwordValidator = new PasswordValidator();
        nameValidator = new NameValidator();
    }

    private static class SingletonHolder {
        public static final ComponentValidatorsProvider HOLDER_INSTANCE = new ComponentValidatorsProvider();
    }

    public static ComponentValidatorsProvider getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public AbstractStringValidator getEmailValidator() {
        return emailValidator;
    }

    public AbstractStringValidator getPasswordValidator() {
        return passwordValidator;
    }

    public AbstractStringValidator getNameValidator() {
        return nameValidator;
    }
}
