package by.epam.jwd.testingApp.service.validation.entitiesValidator;

import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgSupplier;
import by.epam.jwd.testingApp.service.validation.componentValidator.ComponentValidatorsProvider;

public class UserValidator implements AbstractEntitiesValidator<User> {

    public static final String EMPTY_PASSWORD = "password.empty";
    public static final String EMPTY_EMAIL = "email.empty";
    public static final String EMPTY_NAME = "name.empty";

    @Override
    public boolean validateEntity(User entity,String locale,StringBuilder errorMsgAccumulator) {
        if(entity == null) return false;

        boolean error = false;

        boolean needErrorMsg = locale!=null && errorMsgAccumulator!=null;
        ErrorMsgSupplier manager = null;
        if(needErrorMsg) {
            manager = ErrorMsgProvider.getInstance().getManagerByLocale(locale);
        }

        ComponentValidatorsProvider componentValidator = ComponentValidatorsProvider.getInstance();

        String email = entity.getEmail();
        if(email == null){
            error = true;
            if(needErrorMsg) {
                errorMsgAccumulator.append(manager.getValueByName(EMPTY_EMAIL));
            }
        } else if(!componentValidator.getEmailValidator().validate(email,locale,errorMsgAccumulator)){
            error = true;
        }

        String password = entity.getPassword();
        if(password == null){
            error = true;
            if(needErrorMsg) {
                errorMsgAccumulator.append(manager.getValueByName(EMPTY_PASSWORD));
            }
        } else if(!componentValidator.getPasswordValidator().validate(password,locale,errorMsgAccumulator)){
            error = true;
        }

        String name = entity.getName();
        if(name == null){
            error = true;
            if(needErrorMsg) {
                errorMsgAccumulator.append(manager.getValueByName(EMPTY_NAME));
            }
        } else if(!componentValidator.getNameValidator().validate(name,locale,errorMsgAccumulator)){
            error = true;
        }

        return !error;
    }
}
