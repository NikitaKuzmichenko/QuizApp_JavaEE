package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entity.Comment;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractCommentDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.CommentMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoJDBC implements AbstractCommentDao  {

    public static final String SELECT_BY_TEST_ID_SQL = "SELECT * FROM " + CommentMapping.TABLE_NAME
                    +" WHERE " + CommentMapping.TEST_ID + " = ?;";

    public static final String SELECT_BY_ID_SQL = "SELECT * FROM " + CommentMapping.TABLE_NAME
            +" WHERE " + CommentMapping.ID + " = ?;";

    public static final String UPDATE_SQL = "UPDATE " + CommentMapping.TABLE_NAME
            + " SET " + CommentMapping.COMMENT + " = ?, "
            +  CommentMapping.TEST_ID + " = ?, "
            +  CommentMapping.CREATION_DATE + " = ?, "
            +  CommentMapping.CREATOR_ID + " = ?, "
            + "WHERE " + CommentMapping.ID +" = ?;";

    public static final String DELETE_SQL = "DELETE FROM " + CommentMapping.TABLE_NAME
            + " WHERE " + CommentMapping.ID +" = ?;";

    public static final String CREATE_SQL = "INSERT INTO " + CommentMapping.TABLE_NAME
            + " (" + CommentMapping.COMMENT + ", "
            +  CommentMapping.TEST_ID + ", "
            +  CommentMapping.CREATION_DATE + ", "
            +  CommentMapping.CREATOR_ID + " )"
            + "VALUES(?,?,?,?)";

    @Override
    public List<Comment> selectByTestId(int testId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Comment> result;

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
            } catch (SQLException e) {/* write in logs*/}
            pool.returnConnection(connection);
        }
        return result;
    }

    @Override
    public Comment selectEntityById(Integer id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Comment> result;

        try {
            statement = connection.prepareStatement(SELECT_BY_ID_SQL);
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
    public boolean update(Comment entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1,entity.getComment());
            statement.setInt(2,entity.getTestId());
            statement.setDate(3, (Date) entity.getCreationDate());
            statement.setInt(4,entity.getTestId());
            statement.setInt(5,entity.getId());
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
            statement = connection.prepareStatement(DELETE_SQL);
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
    public boolean create(Comment entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            statement = connection.prepareStatement(CREATE_SQL);
            statement.setString(1,entity.getComment());
            statement.setInt(2,entity.getTestId());
            statement.setDate(3, new Date(entity.getCreationDate().getTime()));
            statement.setInt(4,entity.getTestId());
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

    private List<Comment> parsFromResultSet(ResultSet set) throws SQLException {
        List<Comment> result = new ArrayList<>();
        if(set==null) return result;
        while (set.next()){
            result.add(new Comment(
                    set.getInt(CommentMapping.ID),
                    set.getInt(CommentMapping.TEST_ID),
                    set.getInt(CommentMapping.CREATOR_ID),
                    set.getString(CommentMapping.COMMENT),
                    set.getDate(CommentMapping.CREATION_DATE))
            );
        }
        return result;
    }
}
