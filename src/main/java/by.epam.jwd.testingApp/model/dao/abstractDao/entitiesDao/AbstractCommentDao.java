package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.Comment;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractCommentDao extends AbstractGenericDao<Comment, Integer> {

    public List<Comment> getByTestId(Integer testId) throws DaoException;

}
