package by.epam.jwd.testingApp.service.validationService.componentValidator;

import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;

public class NameValidator implements AbstractStringValidator {
    public static final String LONG_NAME = "name.long";

    public static final int MAX_LENGTH = 20;

    @Override
    public  boolean validate(String entity, String locale, StringBuilder errorMsgAccumulator) {
        if(entity == null || locale == null || errorMsgAccumulator == null) return false;

        if(entity.trim().length() > MAX_LENGTH) {
            errorMsgAccumulator.append(ErrorMsgProvider.newInstance().getManagerByLocale(locale)
                    .getValueByName(LONG_NAME)).append('\n');
            return false;
        }
        return true;
    }
}
