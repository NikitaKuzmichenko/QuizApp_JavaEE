package by.epam.jwd.testingApp.service.entitiesService.serviceImpl;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractUserDao;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractUserService;

import java.util.LinkedList;
import java.util.List;

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

    @Override
    public List<User> selectTestCreators(List<Test> tests) throws ServiceException {
        List<User> result = new LinkedList<>();

        try {
            AbstractUserDao userDao = DaoFactory.getInstance().getUserDao();
            for (Test test: tests) {
                result.add(userDao.selectEntityById(test.getCreatorId()));
            }
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }

        return result;
    }
}
