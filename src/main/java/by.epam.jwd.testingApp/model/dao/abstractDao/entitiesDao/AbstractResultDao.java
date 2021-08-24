package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractResultDao extends AbstractGenericDao<Result, Integer> {

    public List<Result> getByUserId(Integer userId,int limit,int offset) throws DaoException;

    public List<Result> getByTestId(Integer testId) throws DaoException;

    public Integer getAvgResultByUserId(Integer userId) throws DaoException;

    public Integer getAvgResultByTestId(Integer testId) throws DaoException;

}
