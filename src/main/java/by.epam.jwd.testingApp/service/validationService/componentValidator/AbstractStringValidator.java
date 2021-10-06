package by.epam.jwd.testingApp.service.validationService.componentValidator;

public interface AbstractStringValidator {

    String ruLocale = "ru";
    String euLocale = "eu";

    boolean validate(String entity, String locale, StringBuilder errorMsgAccumulator);
}
