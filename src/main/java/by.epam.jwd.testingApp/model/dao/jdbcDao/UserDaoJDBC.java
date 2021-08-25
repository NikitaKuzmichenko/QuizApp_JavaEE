package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractUserDao;

import java.sql.ResultSet;
import java.util.List;

public class UserDaoJDBC implements AbstractUserDao {

    public UserDaoJDBC() throws DaoException {}

    private List<User> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public User selectEntityByLoginPassword(String login, String password) throws DaoException {
        return null;
    }

    @Override
    public User selectEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public User update(User entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(User entity) throws DaoException {
        return false;
    }
}
