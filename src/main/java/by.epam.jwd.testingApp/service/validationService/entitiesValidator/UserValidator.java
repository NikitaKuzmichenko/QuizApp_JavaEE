package by.epam.jwd.testingApp.service.validationService.entitiesValidator;

import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgSupplier;
import by.epam.jwd.testingApp.service.validationService.componentValidator.AbstractStringValidator;
import by.epam.jwd.testingApp.service.validationService.componentValidator.EmailValidator;
import by.epam.jwd.testingApp.service.validationService.componentValidator.NameValidator;
import by.epam.jwd.testingApp.service.validationService.componentValidator.PasswordValidator;

public class UserValidator implements AbstractEntitiesValidator<User> {

    public static final String EMPTY_PASSWORD = "password.empty";
    public static final String EMPTY_EMAIL = "email.empty";
    public static final String EMPTY_NAME = "name.empty";

    @Override
    public boolean validateEntity(User entity,String locale,StringBuilder errorMsgAccumulator) {
        if(entity == null || locale == null || errorMsgAccumulator == null) return false;

        boolean error = false;
        ErrorMsgSupplier manager = ErrorMsgProvider.newInstance().getManagerByLocale(locale);

        AbstractStringValidator stringValidator = new EmailValidator();
        String email = entity.getEmail();
        if(email == null){
            error = true;
            errorMsgAccumulator.append(manager.getValueByName(EMPTY_EMAIL)).append('\n');
        } else if(stringValidator.validate(email,locale,errorMsgAccumulator)){
            error = true;
        }

        stringValidator = new PasswordValidator();
        String password = entity.getEmail();
        if(password == null){
            error = true;
            errorMsgAccumulator.append(manager.getValueByName(EMPTY_PASSWORD)).append('\n');
        } else if(stringValidator.validate(password,locale,errorMsgAccumulator)){
            error = true;
        }

        stringValidator = new NameValidator();
        String name = entity.getEmail();
        if(name == null){
            error = true;
            errorMsgAccumulator.append(manager.getValueByName(EMPTY_NAME)).append('\n');
        } else if(stringValidator.validate(name,locale,errorMsgAccumulator)){
            error = true;
        }

        return error;
    }
}
