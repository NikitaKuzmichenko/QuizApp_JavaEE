package by.epam.jwd.testingApp.service.entitiesService.serviceImpl;

import by.epam.jwd.testingApp.entities.Pair;
import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractResultDao;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractResultService;

import java.util.ArrayList;
import java.util.List;

public class ResultService implements AbstractResultService {

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
                    calculateResultsNumberByTestId(testId);
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
    public List<Result> selectByUserId(int userId, int offset,int limit) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectByUserId(userId,limit,offset);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Double> calculateAvgResultsByTestId(List<Test> tests) throws ServiceException {
        List<Double> results = new ArrayList<>();
        try {
            AbstractResultDao dao= DaoFactory.getInstance().getResultDao();
            for(Test test : tests) {
                if (dao.calculateResultsNumberByTestId(test.getId()) == 0) {
                    results.add(-1d);
                } else {
                    results.add((double) dao.calculateAvgResultByTestId(test.getId()) / 100d);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
        return results;
    }
}
