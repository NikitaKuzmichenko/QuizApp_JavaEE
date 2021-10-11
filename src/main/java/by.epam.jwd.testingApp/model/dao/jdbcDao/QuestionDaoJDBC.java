package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entities.Question;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractQuestionDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.QuestionMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoJDBC implements AbstractQuestionDao {

    private List<Question> parsFromResultSet(ResultSet set) throws SQLException {
        List<Question> result = new ArrayList<>();
        if(set==null) return result;
        while (set.next()){
            result.add(new Question(
                    set.getInt(QuestionMapping.ID),
                    set.getInt(QuestionMapping.TEST_ID),
                    set.getString(QuestionMapping.TITLE))
            );
        }
        return result;
    }

    @Override
    public List<Question> selectByTestId(int testId, int limit, int offset) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Question> result;

        try {
            String sql = "SELECT * FROM " + QuestionMapping.TABLE_NAME
                    +" WHERE " + QuestionMapping.TEST_ID + " = ?"
                    +" LIMIT " + limit
                    + " OFFSET " + offset +";";
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
    public Question selectEntityById(Integer id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Question> result;

        try {
            String sql = "SELECT * FROM " + QuestionMapping.TABLE_NAME
                    +" WHERE " + QuestionMapping.ID + " = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
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
    public boolean update(Question entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            String sql = "UPDATE " + QuestionMapping.TABLE_NAME
                    + " SET " + QuestionMapping.TEST_ID + " = ?, "
                    +  QuestionMapping.TITLE + " = ? "
                    + " WHERE " + QuestionMapping.ID +" = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,entity.getTestId());
            statement.setString(2,entity.getTitle());
            statement.setInt(3,entity.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {/* write in logs*/}
            pool.returnConnection(connection);
        }
        return result==1;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            String sql = "DELETE FROM " + QuestionMapping.TABLE_NAME
                    + " WHERE " + QuestionMapping.ID +" = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {/* write in logs*/}
            pool.returnConnection(connection);
        }
        return result==1;
    }

    @Override
    public boolean create(Question entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            String sql = "INSERT INTO " + QuestionMapping.TABLE_NAME
                    + " (" + QuestionMapping.TEST_ID + ", "
                    +  QuestionMapping.TITLE + " )"
                    + "VALUES(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,entity.getTestId());
            statement.setString(2,entity.getTitle());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {/* write in logs*/}
            pool.returnConnection(connection);
        }
        return result==1;
    }

    public Integer createAndGetId(Question entity) throws DaoException{
        if(entity == null) return null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        Integer key = null;
        int result;
        try {
            String sql = "INSERT INTO " + QuestionMapping.TABLE_NAME
                    + " (" + QuestionMapping.TEST_ID + ", "
                    +  QuestionMapping.TITLE + " )"
                    + "VALUES(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,entity.getTestId());
            statement.setString(2,entity.getTitle());
            result = statement.executeUpdate();

            if(result == 1){
                ResultSet set = statement.executeQuery("SELECT LAST_INSERT_ID()");
                if(set.next()){
                    key = set.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {/* write in logs*/}
            pool.returnConnection(connection);
        }

        return key;
    }
}
