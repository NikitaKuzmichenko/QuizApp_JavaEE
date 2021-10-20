package by.epam.jwd.testingApp.service.validation.entitiesValidator;

public interface AbstractEntitiesValidator<E> {
    boolean validateEntity(E entity,String locale,StringBuilder errorMsgAccumulator);
}
