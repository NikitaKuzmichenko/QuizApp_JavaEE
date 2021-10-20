package by.epam.jwd.testingApp.service.entity.serviceImpl;

import by.epam.jwd.testingApp.entity.Pair;
import by.epam.jwd.testingApp.entity.Result;
import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exception.DaoException;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractResultDao;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.model.dataBaseMapping.ResultMapping;
import by.epam.jwd.testingApp.service.entity.abstractService.AbstractResultService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResultService implements AbstractResultService {

    @Override
    public boolean create(Result entity) throws ServiceException {
        if(entity==null)return false;
        try {
            return DaoFactory.getInstance().getResultDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Result entity) throws ServiceException {
        if(entity==null)return false;
        try {
            return DaoFactory.getInstance().getResultDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Pair<Integer,Integer> id) throws ServiceException {
        if(id==null)return false;
        try {
            return DaoFactory.getInstance().getResultDao().
                    delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Result selectEntityById(Pair<Integer,Integer> id) throws ServiceException {
        if(id==null)return null;
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer calculateRowsNumberByTestId(int testId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    calculateResultsNumber(ResultMapping.TEST_ID,testId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer calculateRowsNumberByUserId(int userId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    calculateResultsNumber(ResultMapping.USER_ID,userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Integer> calculateRowsNumberByTestId(List<Test> tests) throws ServiceException{
        if(tests==null)return null;
        List<Integer> result = new LinkedList<>();
        for(Test test:tests) {
            result.add(calculateRowsNumberByTestId(test.getId()));
        }
        return result;
    }

    @Override
    public Integer calculateAvgResultByTestId(int testId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    calculateAvgResultByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Result> selectByTestId(int testId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Result> selectByUserId(int userId, int offset,int limit) throws ServiceException{
        try {
            return DaoFactory.getInstance().getResultDao().
                    selectByUserId(userId,limit,offset);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Double> calculateAvgResultsByTestId(List<Test> tests) throws ServiceException {
        if(tests==null)return null;
        List<Double> results = new ArrayList<>();
        try {
            AbstractResultDao dao= DaoFactory.getInstance().getResultDao();
            for(Test test : tests) {
                if (dao.calculateResultsNumber(ResultMapping.TEST_ID,test.getId()) == 0) {
                    results.add(-1d);
                } else {
                    results.add((double) dao.calculateAvgResultByTestId(test.getId()) / 100d);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return results;
    }
}
