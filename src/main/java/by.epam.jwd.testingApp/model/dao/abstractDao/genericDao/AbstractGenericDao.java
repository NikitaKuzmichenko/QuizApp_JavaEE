package by.epam.jwd.testingApp.model.dao.abstractDao.genericDao;

import by.epam.jwd.testingApp.exception.DaoException;

public interface AbstractGenericDao<E,K>{
    public  E selectEntityById(K id) throws DaoException;
    public  boolean update(E entity) throws DaoException;
    public  boolean delete(K id) throws DaoException;
    public  boolean create(E entity) throws DaoException;
}
