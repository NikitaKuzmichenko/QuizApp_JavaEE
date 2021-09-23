package by.epam.jwd.testingApp.service;

import by.epam.jwd.testingApp.entities.Pair;
import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;

import java.util.List;

public class ResultService {

    public static final int LimitOnPage = 10;

    public static boolean createResult(Result entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getResultDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean updateResult(Result entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getResultDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean deleteResult(Pair<Integer,Integer> id) throws ServiceException {
        try {
            return DaoFactory.getInstance().getResultDao().
                    delete(id);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static Result selectResultById(Pair<Integer,Integer> id) throws ServiceException {
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static Integer calculateRowsNumberByTestId(int testId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    calculateRowsNumberByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
    public static Integer calculateAvgResultByTestId(int testId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    calculateAvgResultByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
    public static Integer calculateAvgResultByUserId(int userId)throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    calculateAvgResultByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
    public static List<Result> selectByTestId(int testId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
    public static List<Result> selectByUserId(int userId, int offset) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectByUserId(userId,LimitOnPage,offset);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

}
