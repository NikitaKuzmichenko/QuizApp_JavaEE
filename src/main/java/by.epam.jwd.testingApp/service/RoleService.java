package by.epam.jwd.testingApp.service;

import by.epam.jwd.testingApp.entities.role.Role;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;

import java.util.List;

public class RoleService {
    public static boolean createRole(Role entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean updateRole(Role entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean deleteRole(int roleId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao().
                    delete(roleId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static Role selectStatementById(int roleId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao().
                    selectEntityById(roleId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static List<Role> selectAllRoles() throws ServiceException {
        try {
            return DaoFactory.getInstance().getRoleDao()
                    .selectAll();
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
