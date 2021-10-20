package by.epam.jwd.testingApp.service.validation.componentValidator;

public interface AbstractStringValidator {

    String ruLocale = "ru";
    String euLocale = "eu";

    boolean validate(String entity, String locale, StringBuilder errorMsgAccumulator);
}
