package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.testComponents.Question;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractQuestionDao extends AbstractGenericDao<Question, Integer> {

    public List<Question> getByTestId(Integer testId,int limit,int offset) throws DaoException;

}
