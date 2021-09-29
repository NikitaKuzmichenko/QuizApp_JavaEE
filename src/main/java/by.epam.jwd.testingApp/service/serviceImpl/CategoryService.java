package by.epam.jwd.testingApp.service.serviceImpl;

import by.epam.jwd.testingApp.entities.category.Category;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.abstractService.AbstractCategoryService;

import java.util.List;

public class CategoryService implements AbstractCategoryService {

    @Override
    public  boolean create(Category entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public  boolean update(Category entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean delete(Integer categoryId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    delete(categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public  Category selectEntityById(Integer categoryId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    selectEntityById(categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public  List<Category> selectAll() throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao()
                    .selectAll();
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
