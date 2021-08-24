package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.testComponents.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractTestDao extends AbstractGenericDao<Test, Integer> {

    public List<Test> getPreviewByCreatorId(Integer creatorId,int limit,int offset) throws DaoException;

    public List<Test> getPreviewByCategory(String category,int limit,int offset) throws DaoException;

    public List<Test> getPreviewByName(String name,int limit,int offset) throws DaoException;

}
