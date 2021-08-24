package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.entities.testComponents.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractUserDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;
import by.epam.jwd.testingApp.model.dao.jdbcDao.connctionPoolClient.ConnectionPoolClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class UserDaoJDBC extends ConnectionPoolClient implements AbstractUserDao {

    public UserDaoJDBC() throws DaoException {}

    private List<User> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public User getEntityByLoginPassword(String login, String password) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public User getEntityById(Integer id) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public User update(User entity) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        Connection connection = getConnection();
        return false;
    }

    @Override
    public boolean create(User entity) throws DaoException {
        Connection connection = getConnection();
        return false;
    }
}
