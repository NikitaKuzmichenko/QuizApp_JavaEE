package by.epam.jwd.testingApp.service.serviceImpl;

import by.epam.jwd.testingApp.entities.Pair;
import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.abstractService.AbstractResultService;

import java.util.List;

public class ResultService implements AbstractResultService {

    public static final int LimitOnPage = 10;

    @Override
    public boolean create(Result entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getResultDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean update(Result entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getResultDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean delete(Pair<Integer,Integer> id) throws ServiceException {
        try {
            return DaoFactory.getInstance().getResultDao().
                    delete(id);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Result selectEntityById(Pair<Integer,Integer> id) throws ServiceException {
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Integer calculateRowsNumberByTestId(int testId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    calculateRowsNumberByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Integer calculateAvgResultByTestId(int testId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    calculateAvgResultByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Integer calculateAvgResultByUserId(int userId)throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    calculateAvgResultByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Result> selectByTestId(int testId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Result> selectByUserId(int userId, int offset) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectByUserId(userId,LimitOnPage,offset);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
