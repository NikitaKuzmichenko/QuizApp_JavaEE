package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Statement;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractStatementDao;

import java.sql.ResultSet;
import java.util.List;

public class StatementDaoJDBC implements AbstractStatementDao {

    public StatementDaoJDBC() throws DaoException {}

    private List<Statement> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public List<Statement> selectByQuestionId(Integer questionId) throws DaoException {
        return null;
    }

    @Override
    public Statement selectEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Statement update(Statement entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Statement entity) throws DaoException {
        return false;
    }
}
