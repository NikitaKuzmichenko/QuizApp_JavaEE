package by.epam.jwd.testingApp.service;

import by.epam.jwd.testingApp.entities.category.Category;
import by.epam.jwd.testingApp.entities.role.Role;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;

import java.util.List;

public class CategoryService {
    public static boolean createRole(Category entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean updateRole(Category entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean deleteRole(int categoryId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    delete(categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static Category selectStatementById(int categoryId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    selectEntityById(categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static List<Category> selectAllRoles() throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao()
                    .selectAll();
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
