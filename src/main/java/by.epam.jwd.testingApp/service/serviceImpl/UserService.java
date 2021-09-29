package by.epam.jwd.testingApp.service.serviceImpl;

import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.abstractService.AbstractUserService;

public class UserService implements AbstractUserService {

    @Override
    public boolean create(User entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean update(User entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean delete(Integer entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public User selectEntityById(Integer userId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    selectEntityById(userId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public User selectByLoginPassword(String email,String password) throws ServiceException {
        try {
            return DaoFactory.getInstance().getUserDao().
                    selectEntityByLoginPassword(email,password);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
