package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Question;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractQuestionDao;

import java.sql.ResultSet;
import java.util.List;

public class QuestionDaoJDBC implements AbstractQuestionDao {

    public QuestionDaoJDBC() throws DaoException {}

    private List<Question> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public List<Question> selectByTestId(Integer testId, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public Question selectEntityById(Integer id) throws DaoException {
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
