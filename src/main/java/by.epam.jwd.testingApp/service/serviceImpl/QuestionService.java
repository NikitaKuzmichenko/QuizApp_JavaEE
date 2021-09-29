package by.epam.jwd.testingApp.service.serviceImpl;

import by.epam.jwd.testingApp.entities.Question;
import by.epam.jwd.testingApp.entities.Statement;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.abstractService.AbstractQuestionService;

import java.util.List;

public class QuestionService implements AbstractQuestionService {

    public static final int LimitOnPage = 10;

    @Override
    public boolean create(Question entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean update(Question entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean delete(Integer entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Question selectEntityById(Integer statementId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    selectEntityById(statementId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Question> selectEntityByTestId(int testId, int offset)throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    selectByTestId(testId,LimitOnPage,offset);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
