package by.epam.jwd.testingApp.service.entity.abstractService;

import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractStatementService extends AbstractEntitiesService<Statement,Integer> {
    List<Statement> selectByQuestionId(int questionId) throws ServiceException;

    boolean deleteAllByQuestionId(int questionId) throws ServiceException;
}
