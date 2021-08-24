package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.testComponents.Statement;
import by.epam.jwd.testingApp.entities.testComponents.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractTestDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;
import by.epam.jwd.testingApp.model.dao.jdbcDao.connctionPoolClient.ConnectionPoolClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class TestDaoJDBC extends ConnectionPoolClient implements AbstractTestDao {

    public TestDaoJDBC() throws DaoException {}

    private List<Test> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public List<Test> getPreviewByCreatorId(Integer creatorId, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public List<Test> getPreviewByCategory(String category, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public List<Test> getPreviewByName(String name, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public Test getEntityById(Integer id) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public Test update(Test entity) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        Connection connection = getConnection();
        return false;
    }

    @Override
    public boolean create(Test entity) throws DaoException {
        Connection connection = getConnection();
        return false;
    }
}
