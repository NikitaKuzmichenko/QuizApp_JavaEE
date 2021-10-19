package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entity.Category;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractCategoryDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.CategoryMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoJDBC implements AbstractCategoryDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String IS_ROW_EXIST_SQL = "SELECT * FROM " + CategoryMapping.TABLE_NAME
                + " WHERE " + CategoryMapping.NAME + " = ?;";

    public static final String SELECT_ALL_SQL = "SELECT * FROM " + CategoryMapping.TABLE_NAME + ";";

    public static final String SELECT_BY_ID_SQL = "SELECT * FROM " + CategoryMapping.TABLE_NAME
            + " WHERE " + CategoryMapping.ID + " = ?;";

    public static final String UPDATE_SQL = "UPDATE " + CategoryMapping.TABLE_NAME
                    + " SET " + CategoryMapping.NAME + " = ? "
            + "WHERE " + CategoryMapping.ID +" = ?;";

    public static final String DELETE_SQL = "DELETE FROM " + CategoryMapping.TABLE_NAME
            + " WHERE " + CategoryMapping.ID +" = ?;";

    public static final String CREATE_SQL = "INSERT INTO " + CategoryMapping.TABLE_NAME
            + " (" + CategoryMapping.NAME + ")"
            +  "VALUES(?)";


    @Override
    public List<Category> selectAll() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Category> result;

        try {
            statement = connection.prepareStatement(SELECT_ALL_SQL);
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
    public Category selectEntityById(Integer id) throws DaoException {
        if(id==null) return null;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Category> result;

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
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return (result.size() == 1) ? result.get(0) : null;
    }

    @Override
    public boolean update(Category entity) throws DaoException {
        if(entity == null) return false;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        int result;

        try {
            if(isRowExist(entity,connection))return false;

            statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1,entity.getName());
            statement.setInt(2,entity.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result==1;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        if(id==null) return false;

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
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result==1;
    }

    @Override
    public boolean create(Category entity) throws DaoException {
        if(entity == null) return false;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        int result;

        try {
            if(isRowExist(entity,connection))return false;

            statement = connection.prepareStatement(CREATE_SQL);
            statement.setString(1,entity.getName());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result==1;
    }

    private List<Category> parsFromResultSet(ResultSet set) throws SQLException {
        List<Category> result = new ArrayList<>();
        if(set==null) return result;
        while (set.next()){
            result.add(new Category(set.getInt(CategoryMapping.ID),set.getString(CategoryMapping.NAME)));
        }
        return result;
    }

    private boolean isRowExist(Category entity,Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(IS_ROW_EXIST_SQL);
        statement.setString(1,entity.getName());
        ResultSet resultSet = statement.executeQuery();

        boolean result = resultSet.next();
        resultSet.close();
        statement.close();

        return  result;
    }
}
