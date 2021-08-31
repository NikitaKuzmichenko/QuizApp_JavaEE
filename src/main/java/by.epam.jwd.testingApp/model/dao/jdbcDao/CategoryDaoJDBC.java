package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entities.Category;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractCategoryDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.CategoryMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoJDBC implements AbstractCategoryDao {

    private List<Category> parsFromResultSet(ResultSet set) throws SQLException {
        List<Category> result = new ArrayList<>();
        if(set==null) return result;
        while (set.next()){
            result.add(new Category(set.getInt(CategoryMapping.ID),set.getString(CategoryMapping.NAME)));
        }
        return result;
    }

    private boolean isRowExist(Category entity,Connection connection)
            throws SQLException {

        String selectSql = "SELECT * FROM " + CategoryMapping.TABLE_NAME
                + " WHERE " + CategoryMapping.NAME + " = ?;";

        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setString(1,entity.getName());
        ResultSet resultSet = statement.executeQuery();

        boolean result = resultSet.next();
        resultSet.close();
        statement.close();

        return  result;
    }

    @Override
    public List<Category> selectAll() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Category> result;

        try {
            String sql = "SELECT * FROM " + CategoryMapping.TABLE_NAME + ";";
            statement = connection.prepareStatement(sql);
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
    public Category selectEntityById(Integer id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Category> result;

        try {
            String sql = "SELECT * FROM " + CategoryMapping.TABLE_NAME
                    + " WHERE " + CategoryMapping.ID + " = ?;";
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
    public boolean update(Category entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result = 0;
        try {
            if(isRowExist(entity,connection))return false;

            String updateSql = "UPDATE " + CategoryMapping.TABLE_NAME
                    + " SET " + CategoryMapping.NAME + " = ? "
                    + "WHERE " + CategoryMapping.ID +" = ?;";
            statement = connection.prepareStatement(updateSql);
            statement.setString(1,entity.getName());
            statement.setInt(2,entity.getId());
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
        int result = 0;
        try {
            String sql = "DELETE FROM " + CategoryMapping.TABLE_NAME
                    + " WHERE " + CategoryMapping.ID +" = ?;";
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
    public boolean create(Category entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result = 0;
        try {
            if(isRowExist(entity,connection))return false;

            String insectSql = "INSERT INTO " + CategoryMapping.TABLE_NAME
                    + " (" + CategoryMapping.NAME + ")"
                    +  "VALUES(?)";

            statement = connection.prepareStatement(insectSql);
            statement.setString(1,entity.getName());
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
