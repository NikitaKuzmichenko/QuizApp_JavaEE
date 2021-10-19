package by.epam.jwd.testingApp.service.entitiesService.serviceImpl;

import by.epam.jwd.testingApp.entity.Category;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractCategoryService;

import java.util.List;

public class CategoryService implements AbstractCategoryService {

    @Override
    public  boolean create(Category entity) throws ServiceException {
        if(entity==null) return false;
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public  boolean update(Category entity) throws ServiceException {
        if(entity==null) return false;
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer categoryId) throws ServiceException {
        if(categoryId==null) return false;
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    delete(categoryId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public  Category selectEntityById(Integer categoryId) throws ServiceException {
        if(categoryId==null) return null;
        try {
            return DaoFactory.getInstance().getCategoryDao().
                    selectEntityById(categoryId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public  List<Category> selectAll() throws ServiceException {
        try {
            return DaoFactory.getInstance().getCategoryDao()
                    .selectAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
