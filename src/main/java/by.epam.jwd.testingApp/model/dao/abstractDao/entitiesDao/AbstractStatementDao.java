package by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao;

import by.epam.jwd.testingApp.entities.Statement;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;

import java.util.List;

public interface AbstractStatementDao extends AbstractGenericDao<Statement, Integer> {

    public List<Statement> selectByQuestionId(Integer questionId)  throws DaoException;

}
