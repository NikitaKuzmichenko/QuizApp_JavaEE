package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractTestDao extends AbstractGenericDao<Test, Integer> {

    public List<Test> selectPreviewByCreatorId(Integer creatorId, int limit, int offset) throws DaoException;

    public List<Test> selectPreviewByCategory(String category, int limit, int offset) throws DaoException;

    public List<Test> selectPreviewByName(String name, int limit, int offset) throws DaoException;

}
