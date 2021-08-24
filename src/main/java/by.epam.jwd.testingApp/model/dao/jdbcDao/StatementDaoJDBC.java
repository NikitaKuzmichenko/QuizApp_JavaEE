package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Role;
import by.epam.jwd.testingApp.entities.testComponents.Statement;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractStatementDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;
import by.epam.jwd.testingApp.model.dao.jdbcDao.connctionPoolClient.ConnectionPoolClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class StatementDaoJDBC extends ConnectionPoolClient implements AbstractStatementDao {


    public StatementDaoJDBC() throws DaoException {}

    private List<Statement> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public List<Statement> getByQuestionId(Integer questionId) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public Statement getEntityById(Integer id) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public Statement update(Statement entity) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        Connection connection = getConnection();
        return false;
    }

    @Override
    public boolean create(Statement entity) throws DaoException {
        Connection connection = getConnection();
        return false;
    }
}
