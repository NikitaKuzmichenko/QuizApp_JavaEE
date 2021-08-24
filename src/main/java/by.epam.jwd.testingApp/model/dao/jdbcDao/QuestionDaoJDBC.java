package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Comment;
import by.epam.jwd.testingApp.entities.testComponents.Question;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractQuestionDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;
import by.epam.jwd.testingApp.model.dao.jdbcDao.connctionPoolClient.ConnectionPoolClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class QuestionDaoJDBC extends ConnectionPoolClient implements AbstractQuestionDao {

    public QuestionDaoJDBC() throws DaoException {}

    private List<Question> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public List<Question> getByTestId(Integer testId, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public Question getEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Question update(Question entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Question entity) throws DaoException {
        return false;
    }
}
