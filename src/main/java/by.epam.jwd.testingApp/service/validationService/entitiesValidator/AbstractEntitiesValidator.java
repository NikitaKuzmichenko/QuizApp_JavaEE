package by.epam.jwd.testingApp.service.validationService.entitiesValidator;

public interface AbstractEntitiesValidator<E> {
    boolean validateEntity(E entity,String locale,StringBuilder errorMsgAccumulator);
}
