package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entities.Pair;
import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractResultDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.ResultMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoJDBC implements AbstractResultDao {

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

    @Override
    public Result selectEntityById(Pair<Integer, Integer> id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Result> result;

        try {
            String sql = "SELECT * FROM " + ResultMapping.TABLE_NAME
                    +" WHERE " + ResultMapping.TEST_ID + " = ?"
                    + " AND " + ResultMapping.USER_ID + " = ?;";
            statement = connection.prepareStatement(sql);
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
            } catch (SQLException e) {/* write in logs*/}
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
            String sql = "UPDATE " + ResultMapping.TABLE_NAME
                    +" SET " + ResultMapping.RESULT + " = ?, "
                    + ResultMapping.PASSING_DATE + " = ? "
                    +" WHERE " + ResultMapping.TEST_ID + " = ?"
                    + " AND " + ResultMapping.USER_ID + " = ?;";
            statement = connection.prepareStatement(sql);
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
            } catch (SQLException e) {/* write in logs*/}
            pool.returnConnection(connection);
        }
        return result !=0;
    }

    @Override
    public boolean delete(Pair<Integer,Integer> id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        int result;

        try {
            String sql = "DELETE FROM " + ResultMapping.TABLE_NAME
                    +" WHERE " + ResultMapping.TEST_ID + " = ?"
                    + " AND " + ResultMapping.USER_ID + " = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id.getT());
            statement.setInt(2,id.getU());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {/* write in logs*/}
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
            String sql = "INSERT INTO " + ResultMapping.TABLE_NAME
                    +"( " + ResultMapping.RESULT + ", "
                    + ResultMapping.PASSING_DATE + ", "
                    + ResultMapping.TEST_ID + ", "
                    + ResultMapping.USER_ID + " )"
                    + " VALUES(?,?,?,?);";
            statement = connection.prepareStatement(sql);
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
            } catch (SQLException e) {/* write in logs*/}
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
            } catch (SQLException e) {/* write in logs*/}
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
            String sql = "SELECT * FROM " + ResultMapping.TABLE_NAME
                    +" WHERE " + ResultMapping.TEST_ID + " = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,testId);
            resultSet = statement.executeQuery();
            result = parsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {/* write in logs*/}
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
            String sql = "SELECT AVG(" + ResultMapping.RESULT + ")"+
                    " FROM " + ResultMapping.TABLE_NAME
                    +" WHERE " + ResultMapping.TEST_ID + " = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,testId);
            resultSet = statement.executeQuery();
            resultSet.next();
            result =  resultSet.getInt("AVG(" + ResultMapping.RESULT + ")");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {/* write in logs*/}
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
            sqlBuilder.append("SELECT COUNT(" + ResultMapping.RESULT + ")");
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
            result =  resultSet.getInt("COUNT(" + ResultMapping.RESULT + ")");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {/* write in logs*/}
            pool.returnConnection(connection);
        }
        return result;
    }
}
