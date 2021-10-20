package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entity.Role;
import by.epam.jwd.testingApp.exception.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractRoleDao extends AbstractGenericDao<Role,Integer> {

    List<Role> selectAll()throws DaoException;
}
