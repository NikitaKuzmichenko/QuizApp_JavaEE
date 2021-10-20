package by.epam.jwd.testingApp.service.validation.componentValidator;

import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;

public class NameValidator implements AbstractStringValidator {
    public static final String LONG_NAME = "name.long";
    public static final String SHORT_NAME = "name.short";

    public static final int MAX_LENGTH = 20;

    public static final int MIN_LENGTH = 1;

    @Override
    public  boolean validate(String entity, String locale, StringBuilder errorMsgAccumulator) {
        if(entity == null) return false;

        if(entity.trim().length() > MAX_LENGTH) {
            if(errorMsgAccumulator!=null || locale!=null) {
                errorMsgAccumulator.append(ErrorMsgProvider.getInstance().getManagerByLocale(locale)
                        .getValueByName(LONG_NAME));
            }
            return false;
        }

        if(entity.trim().length() < MIN_LENGTH) {
            if(errorMsgAccumulator!=null || locale!=null) {
                errorMsgAccumulator.append(ErrorMsgProvider.getInstance().getManagerByLocale(locale)
                        .getValueByName(SHORT_NAME));
            }
            return false;
        }
        return true;
    }
}
