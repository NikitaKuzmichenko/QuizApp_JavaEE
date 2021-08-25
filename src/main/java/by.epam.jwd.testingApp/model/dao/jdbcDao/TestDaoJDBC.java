package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractTestDao;

import java.sql.ResultSet;
import java.util.List;

public class TestDaoJDBC implements AbstractTestDao {

    public TestDaoJDBC() throws DaoException {}

    private List<Test> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public List<Test> selectPreviewByCreatorId(Integer creatorId, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public List<Test> selectPreviewByCategory(String category, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public List<Test> selectPreviewByName(String name, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public Test selectEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Test update(Test entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Test entity) throws DaoException {
        return false;
    }
}
