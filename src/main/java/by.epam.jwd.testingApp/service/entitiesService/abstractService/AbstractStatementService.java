package by.epam.jwd.testingApp.service.entitiesService.abstractService;

import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractStatementService extends AbstractEntitiesService<Statement,Integer> {
    List<Statement> selectByQuestionId(int questionId) throws ServiceException;

    boolean deleteAllByQuestionId(int questionId) throws ServiceException;
}
