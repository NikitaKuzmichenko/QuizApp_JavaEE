package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.Pair;
import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractResultDao extends AbstractGenericDao<Result, Pair<Integer,Integer>> {

    List<Result> selectByUserId(int userId, int limit, int offset) throws DaoException;

    List<Result> selectByTestId(int testId) throws DaoException;

    Integer calculateAvgResultByUserId(int userId) throws DaoException;

    Integer calculateAvgResultByTestId(int testId) throws DaoException;

    Integer calculateResultsNumberByTestId(int testId) throws DaoException;

}
