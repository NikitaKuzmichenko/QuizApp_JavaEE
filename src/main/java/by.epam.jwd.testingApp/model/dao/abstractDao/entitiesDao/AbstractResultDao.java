package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entity.Pair;
import by.epam.jwd.testingApp.entity.Result;
import by.epam.jwd.testingApp.exception.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractResultDao extends AbstractGenericDao<Result, Pair<Integer,Integer>> {

    List<Result> selectByUserId(int userId, int limit, int offset) throws DaoException;

    List<Result> selectByTestId(int testId) throws DaoException;

    Integer calculateAvgResultByTestId(int testId) throws DaoException;

    Integer calculateResultsNumber(String rowName,int rowValue) throws DaoException;

}
