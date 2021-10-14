package by.epam.jwd.testingApp.service.entitiesService.serviceImpl;

import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractQuestionService;

import java.util.List;

public class QuestionService implements AbstractQuestionService {

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
    public List<Question> selectEntityByTestId(int testId, int offset,int limit)throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    selectByTestId(testId,limit,offset);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Question> selectEntityByTestId(int testId)throws ServiceException {
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    selectByTestId(testId,Integer.MAX_VALUE,0);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public int calculateQuestionNumber(int testId)throws ServiceException{
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    calculateQuestionNumber(testId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Integer createAndGetId(Question entity) throws ServiceException{
        try {
            return DaoFactory.getInstance().getQuestionDao().
                    createAndGetId(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
