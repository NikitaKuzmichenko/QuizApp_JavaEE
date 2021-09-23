package by.epam.jwd.testingApp.service;

import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;

public class UserService {

    public static boolean createUser(User entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean updateUser(User entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean deleteUser(int entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static User selectUserById(int userId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    selectEntityById(userId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static User selectUserByLoginPassword(String email,String password) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    selectEntityByLoginPassword(email,password);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

}
