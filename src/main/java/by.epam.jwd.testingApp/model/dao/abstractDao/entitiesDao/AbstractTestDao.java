package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractTestDao extends AbstractGenericDao<Test, Integer> {

    public List<Test> selectTestsByIntRow(int limit, int offset,String rowName,int rowValue)throws DaoException;

    public List<Test> sortTestsByRow(int limit, int offset, boolean desc ,String sortedRow) throws DaoException;

    public boolean removeTest(int testId)throws DaoException;

}
