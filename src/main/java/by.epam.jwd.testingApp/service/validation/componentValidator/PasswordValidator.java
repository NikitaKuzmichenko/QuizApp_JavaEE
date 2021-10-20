package by.epam.jwd.testingApp.service.validation.componentValidator;

import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;

public class PasswordValidator implements AbstractStringValidator {

    public static final String SHORT_PASSWORD = "password.short";
    public static final String LONG_PASSWORD = "password.long";

    public static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 30;

    @Override
    public boolean validate(String entity, String locale, StringBuilder errorMsgAccumulator) {
        if(entity == null) return false;

        if(entity.trim().length() < MIN_LENGTH) {
            if(errorMsgAccumulator!=null || locale!=null) {
                errorMsgAccumulator.append(ErrorMsgProvider.getInstance().getManagerByLocale(locale)
                        .getValueByName(SHORT_PASSWORD));
            }
            return false;
        }

        if(entity.trim().length() > MAX_LENGTH){
            if(errorMsgAccumulator!=null || locale!=null) {
                errorMsgAccumulator.append(ErrorMsgProvider.getInstance().getManagerByLocale(locale)
                        .getValueByName(LONG_PASSWORD));
            }
            return false;
        }
        return true;
    }
}
