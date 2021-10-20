package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entity.Pair;
import by.epam.jwd.testingApp.entity.Result;
import by.epam.jwd.testingApp.exception.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractResultDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.ResultMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoJDBC implements AbstractResultDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String SELECT_BY_ID_SQL = "SELECT * FROM " + ResultMapping.TABLE_NAME
            +" WHERE " + ResultMapping.TEST_ID + " = ?"
            + " AND " + ResultMapping.USER_ID + " = ?;";

    public static final String UPDATE_SQL = "UPDATE " + ResultMapping.TABLE_NAME
            +" SET " + ResultMapping.RESULT + " = ?, "
            + ResultMapping.PASSING_DATE + " = ? "
            +" WHERE " + ResultMapping.TEST_ID + " = ?"
            + " AND " + ResultMapping.USER_ID + " = ?;";

    public static final String DELETE_SQL = "DELETE FROM " + ResultMapping.TABLE_NAME
            +" WHERE " + ResultMapping.TEST_ID + " = ?"
            + " AND " + ResultMapping.USER_ID + " = ?;";

    public static final String CREATE_SQL = "INSERT INTO " + ResultMapping.TABLE_NAME
            +"( " + ResultMapping.RESULT + ", "
            + ResultMapping.PASSING_DATE + ", "
            + ResultMapping.TEST_ID + ", "
            + ResultMapping.USER_ID + " )"
            + " VALUES(?,?,?,?);";

    public static final String SELECT_BY_TEST_ID_SQL = "SELECT * FROM " + ResultMapping.TABLE_NAME
            +" WHERE " + ResultMapping.TEST_ID + " = ?;";

    public static final String CALCULATE_AVG = "AVG(" + ResultMapping.RESULT + ")";

    public static final String CALCULATE_AVG_RESULT_SQL = "SELECT "+ CALCULATE_AVG +
            " FROM " + ResultMapping.TABLE_NAME
            +" WHERE " + ResultMapping.TEST_ID + " = ?;";

    public static final String COUNT_RESULTS = "COUNT(" + ResultMapping.RESULT + ")";

    @Override
    public Result selectEntityById(Pair<Integer, Integer> id) throws DaoException {
        if(id==null) return null;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Result> result;

        try {
            statement = connection.prepareStatement(SELECT_BY_ID_SQL);
            statement.setInt(1,id.getT());
            statement.setInt(2,id.getU());
            resultSet = statement.executeQuery();
            result = parsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return (result.size() == 1) ? result.get(0) : null;
    }

    @Override
    public boolean update(Result entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        int result ;

        try {
            statement = connection.prepareStatement(UPDATE_SQL);
            statement.setInt(1,entity.getResult());
            statement.setDate(2,new Date(entity.getPassingDate().getTime()));
            statement.setInt(3,entity.getTestId());
            statement.setInt(4,entity.getUserId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result !=0;
    }

    @Override
    public boolean delete(Pair<Integer,Integer> id) throws DaoException {
        if(id==null) return false;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        int result;

        try {
            statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1,id.getT());
            statement.setInt(2,id.getU());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result!=0;
    }

    @Override
    public boolean create(Result entity) throws DaoException {
        if(entity == null) return false;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        int result;

        try {
            statement = connection.prepareStatement(CREATE_SQL);
            statement.setInt(1,entity.getResult());
            statement.setDate(2,new Date(entity.getPassingDate().getTime()));
            statement.setInt(3,entity.getTestId());
            statement.setInt(4,entity.getUserId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result !=0;
    }

    @Override
    public List<Result> selectByUserId(int userId, int limit, int offset) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Result> result;

        try {
            String sql = "SELECT * FROM " + ResultMapping.TABLE_NAME
                    +" WHERE " + ResultMapping.USER_ID + " = ?"
                    +" LIMIT " + limit
                    + " OFFSET " + offset +";";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet = statement.executeQuery();
            result = parsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result;
    }

    @Override
    public List<Result> selectByTestId(int testId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Result> result;

        try {
            statement = connection.prepareStatement(SELECT_BY_TEST_ID_SQL);
            statement.setInt(1,testId);
            resultSet = statement.executeQuery();
            result = parsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result;
    }


    @Override
    public Integer calculateAvgResultByTestId(int testId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result;

        try {
            statement = connection.prepareStatement(CALCULATE_AVG_RESULT_SQL);
            statement.setInt(1,testId);
            resultSet = statement.executeQuery();
            resultSet.next();
            result =  resultSet.getInt(CALCULATE_AVG);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result;
    }

    @Override
    public Integer calculateResultsNumber(String rowName,int rowValue) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result;

        try {
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT " + COUNT_RESULTS);
            sqlBuilder.append(" FROM " + ResultMapping.TABLE_NAME);
            if(rowName!=null){
                sqlBuilder.append(" WHERE " + rowName + " = ?");
            }
            sqlBuilder.append(";");

            statement = connection.prepareStatement(sqlBuilder.toString());
            if(rowName!=null){
                statement.setInt(1,rowValue);
            }
            resultSet = statement.executeQuery();
            resultSet.next();
            result =  resultSet.getInt(COUNT_RESULTS);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result;
    }

    private List<Result> parsFromResultSet(ResultSet set) throws SQLException {
        List<Result> result = new ArrayList<>();
        if(set==null) return result;
        while (set.next()){
            result.add(new Result(
                    set.getInt(ResultMapping.USER_ID),
                    set.getInt(ResultMapping.TEST_ID),
                    set.getInt(ResultMapping.RESULT),
                    set.getDate(ResultMapping.PASSING_DATE))
            );
        }
        return result;
    }
}
