package by.epam.jwd.testingApp.service;

import by.epam.jwd.testingApp.entities.Question;
import by.epam.jwd.testingApp.entities.Statement;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;

import java.util.List;

public class QuestionService {

    public static final int LimitOnPage = 10;

    public static boolean createQuestion(Question entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean updateQuestion(Question entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean deleteQuestion(int entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static Question selectQuestionById(int statementId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    selectEntityById(statementId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static List<Question> selectQuestionByTestId(int testId, int offset)throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    selectByTestId(testId,LimitOnPage,offset);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
