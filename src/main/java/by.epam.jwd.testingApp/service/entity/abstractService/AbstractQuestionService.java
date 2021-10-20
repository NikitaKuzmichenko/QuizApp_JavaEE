package by.epam.jwd.testingApp.service.entity.abstractService;

import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractQuestionService extends AbstractEntitiesService<Question,Integer> {

    List<Question> selectEntityByTestId(int questionId, int offset,int limit)throws ServiceException;
    List<Question> selectEntityByTestId(int questionId)throws ServiceException;
    int calculateQuestionNumber(int testId)throws ServiceException;

    Integer createAndGetId(Question entity) throws ServiceException;
}
