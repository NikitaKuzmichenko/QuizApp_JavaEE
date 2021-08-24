package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.entities.testComponents.Question;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractResultDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.genericDao.AbstractGenericDao;
import by.epam.jwd.testingApp.model.dao.jdbcDao.connctionPoolClient.ConnectionPoolClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class ResultDaoJDBC extends ConnectionPoolClient implements AbstractResultDao {

    public ResultDaoJDBC() throws DaoException {}

    private List<Result> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public Result getEntityById(Integer id) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public Result update(Result entity) throws DaoException {
        Connection connection = getConnection();
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        Connection connection = getConnection();
        return false;
    }

    @Override
    public boolean create(Result entity) throws DaoException {
        Connection connection = getConnection();
        return false;
    }

    @Override
    public List<Result> getByUserId(Integer userId, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public List<Result> getByTestId(Integer testId) throws DaoException {
        return null;
    }

    @Override
    public Integer getAvgResultByUserId(Integer userId) throws DaoException {
        return null;
    }

    @Override
    public Integer getAvgResultByTestId(Integer testId) throws DaoException {
        return null;
    }
}
