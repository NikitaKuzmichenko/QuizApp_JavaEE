package by.epam.jwd.testingApp.service.entitiesService.abstractService;

import by.epam.jwd.testingApp.entities.Question;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractQuestionService extends AbstractEntitiesService<Question,Integer> {
    List<Question> selectEntityByTestId(int testId, int offset,int limit)throws ServiceException;
}
