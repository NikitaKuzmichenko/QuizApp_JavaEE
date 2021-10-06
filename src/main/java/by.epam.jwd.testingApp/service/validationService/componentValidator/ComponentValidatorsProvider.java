package by.epam.jwd.testingApp.service.validationService.componentValidator;

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
    public static ComponentValidatorsProvider newInstance() {
        ComponentValidatorsProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (ComponentValidatorsProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ComponentValidatorsProvider();
                }
            }
        }
        return localInstance;
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
