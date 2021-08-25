package by.epam.jwd.testingApp.model.dao.jdbcDao;

import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractResultDao;

import java.sql.ResultSet;
import java.util.List;

public class ResultDaoJDBC implements AbstractResultDao {

    public ResultDaoJDBC() throws DaoException {}

    private List<Result> parsFromResultSet(ResultSet set){
        return  null;
    }

    @Override
    public Result selectEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Result update(Result entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Result entity) throws DaoException {
        return false;
    }

    @Override
    public List<Result> selectByUserId(Integer userId, int limit, int offset) throws DaoException {
        return null;
    }

    @Override
    public List<Result> selectByTestId(Integer testId) throws DaoException {
        return null;
    }

    @Override
    public Integer calculateAvgResultByUserId(Integer userId) throws DaoException {
        return null;
    }

    @Override
    public Integer calculateAvgResultByTestId(Integer testId) throws DaoException {
        return null;
    }
}
