package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

public interface AbstractUserDao extends AbstractGenericDao<User, Integer> {

    public User selectEntityByLoginPassword(String email, String password) throws DaoException;
}
