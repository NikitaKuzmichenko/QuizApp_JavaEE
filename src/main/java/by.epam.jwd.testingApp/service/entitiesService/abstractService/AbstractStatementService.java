package by.epam.jwd.testingApp.service.entitiesService.abstractService;

import by.epam.jwd.testingApp.entities.Statement;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService.GenericAbstractService;

import java.util.List;

public interface AbstractStatementService extends GenericAbstractService<Statement,Integer> {
    List<Statement> selectByQuestionId(Integer questionId) throws ServiceException;
}
