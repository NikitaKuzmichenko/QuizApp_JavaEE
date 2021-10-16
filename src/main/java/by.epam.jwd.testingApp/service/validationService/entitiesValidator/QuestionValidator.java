package by.epam.jwd.testingApp.service.validationService.entitiesValidator;

import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;

public class QuestionValidator implements AbstractEntitiesValidator<Question>{

    public static final String LONG_QUESTION_NAME = "question.long";
    public static final String SHORT_QUESTION_NAME = "question.short";

    public static final int MAX_LENGTH = 100;
    public static final int MIN_LENGTH = 1;

    @Override
    public boolean validateEntity(Question entity, String locale, StringBuilder errorMsgAccumulator) {
        if(entity==null) return false;

        if(entity.getTitle().length() > MAX_LENGTH){
            if(locale!=null && errorMsgAccumulator!=null){
                errorMsgAccumulator.append(ErrorMsgProvider.getInstance().getManagerByLocale(locale)
                        .getValueByName(LONG_QUESTION_NAME));
            }
            return false;
        }

        if(entity.getTitle().length() < MIN_LENGTH){
            if(locale!=null && errorMsgAccumulator!=null){
                errorMsgAccumulator.append(ErrorMsgProvider.getInstance().getManagerByLocale(locale)
                        .getValueByName(SHORT_QUESTION_NAME));
            }
            return false;
        }
        return true;
    }
}
