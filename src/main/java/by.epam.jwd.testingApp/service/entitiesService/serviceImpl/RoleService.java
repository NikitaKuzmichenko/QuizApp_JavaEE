package by.epam.jwd.testingApp.service.entitiesService.serviceImpl;

import by.epam.jwd.testingApp.entities.Role;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractRoleService;

import java.util.List;

public class RoleService implements AbstractRoleService {

    @Override
    public boolean create(Role entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean update(Role entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean delete(Integer roleId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao().
                    delete(roleId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Role selectEntityById(Integer roleId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao().
                    selectEntityById(roleId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Role> selectAll() throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao()
                    .selectAll();
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

}
