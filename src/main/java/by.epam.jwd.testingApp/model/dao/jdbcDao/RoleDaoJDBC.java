package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Role;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractRoleDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class RoleDaoJDBC implements AbstractRoleDao {

    public RoleDaoJDBC() throws DaoException {}

    private List<Role> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public Role selectEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Role update(Role entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Role entity) throws DaoException {
        return false;
    }

    @Override
    public List<Role> selectAll() throws DaoException {
        return null;
    }
}
