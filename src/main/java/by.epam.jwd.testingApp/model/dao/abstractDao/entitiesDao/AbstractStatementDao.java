package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.Statement;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractStatementDao extends AbstractGenericDao<Statement, Integer> {

    List<Statement> selectByQuestionId(int questionId)  throws DaoException;

    boolean deleteAllByQuestionId(int questionId)  throws DaoException;
}
