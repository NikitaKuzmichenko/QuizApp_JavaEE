package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.role.Role;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractRoleDao extends AbstractGenericDao<Role,Integer> {

    public List<Role> selectAll()throws DaoException;
}