package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Category;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractCategoryDao;

import java.util.List;

public class CategoryDaoJDBC implements AbstractCategoryDao {
    @Override
    public List<Category> selectAll() throws DaoException {
        return null;
    }

    @Override
    public Category selectEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Category update(Category entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Category entity) throws DaoException {
        return false;
    }
}
