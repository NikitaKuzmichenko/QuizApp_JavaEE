package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractTestDao extends AbstractGenericDao<Test, Integer> {

    public List<Test> selectTestsByCreatorId(int creatorId, int limit, int offset) throws DaoException;

    public List<Test> selectTestsByCategory(int categoryId, int limit, int offset) throws DaoException;

    public List<Test> selectTestsByName(String name, int limit, int offset) throws DaoException;

    public List<Test> sortTestsByName(int limit, int offset, boolean desc) throws DaoException;

    public List<Test> sortTestsByDate(int limit, int offset, boolean desc) throws DaoException;

    public List<Test> sortTestsByPassedNumber(int limit, int offset,boolean desc) throws DaoException;

    public boolean removeTest(int testId)throws DaoException;

}
