package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.Question;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractQuestionDao extends AbstractGenericDao<Question, Integer> {

    List<Question> selectByTestId(int testId, int limit, int offset) throws DaoException;

    Integer createAndGetId(Question entity) throws DaoException;

}
