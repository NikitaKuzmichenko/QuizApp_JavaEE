package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractStatementDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.StatementMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatementDaoJDBC implements AbstractStatementDao {

    private List<Statement> parsFromResultSet(ResultSet set) throws SQLException {
        List<Statement> result = new ArrayList<>();
        if(set==null) return result;
        while (set.next()){
            result.add(new Statement(
                    set.getInt(StatementMapping.ID),
                    set.getInt(StatementMapping.QUESTION_ID),
                    set.getString(StatementMapping.TEXT),
                    set.getBoolean(StatementMapping.IS_CORRECT))
            );
        }
        return result;
    }

    @Override
    public List<Statement> selectByQuestionId(int questionId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Statement> result;

        try {
            String sql = "SELECT * FROM " + StatementMapping.TABLE_NAME
                    +" WHERE " + StatementMapping.QUESTION_ID + " = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,questionId);
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
    public Statement selectEntityById(Integer id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Statement> result;

        try {
            String sql = "SELECT * FROM " + StatementMapping.TABLE_NAME
                    +" WHERE " + StatementMapping.ID + " = ?;";
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
    public boolean update(Statement entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            String sql = "UPDATE " + StatementMapping.TABLE_NAME
                    + " SET " + StatementMapping.QUESTION_ID + " = ?, "
                    +  StatementMapping.TEXT + " = ?, "
                    +  StatementMapping.IS_CORRECT + " = ? "
                    + " WHERE " + StatementMapping.ID +" = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,entity.getQuestionId());
            statement.setString(2,entity.getText());
            statement.setBoolean(3,entity.isCorrect());
            statement.setInt(4,entity.getId());
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
            String sql = "DELETE FROM " + StatementMapping.TABLE_NAME
                    + " WHERE " + StatementMapping.ID +" = ?;";
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

    public boolean deleteAllByQuestionId(int questionId)  throws DaoException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            String sql = "DELETE FROM " + StatementMapping.TABLE_NAME
                    + " WHERE " + StatementMapping.QUESTION_ID +" = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,questionId);
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
    public boolean create(Statement entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            String sql = "INSERT INTO " + StatementMapping.TABLE_NAME
                    + " (" + StatementMapping.QUESTION_ID + ", "
                    +  StatementMapping.TEXT + ", "
                    +  StatementMapping.IS_CORRECT + " )"
                    + " VALUES(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,entity.getQuestionId());
            statement.setString(2,entity.getText());
            statement.setBoolean(3,entity.isCorrect());
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
}
