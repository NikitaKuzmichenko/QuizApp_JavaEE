package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.entities.Role;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractRoleDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;
import by.epam.jwd.testingApp.model.dao.jdbcDao.connctionPoolClient.ConnectionPoolClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class RoleDaoJDBC extends ConnectionPoolClient implements AbstractRoleDao {

    public RoleDaoJDBC() throws DaoException {}

    private List<Role> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public Role getEntityById(Integer id) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public Role update(Role entity) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        Connection connection = getConnection();
        return false;
    }

    @Override
    public boolean create(Role entity) throws DaoException {
        Connection connection = getConnection();
        return false;
    }

    @Override
    public List<Role> getAll() throws DaoException {
        Connection connection = getConnection();
        return null;
    }
}
