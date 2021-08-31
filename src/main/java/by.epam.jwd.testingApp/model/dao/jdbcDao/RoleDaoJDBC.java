package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entities.Role;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractRoleDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.RoleMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoJDBC implements AbstractRoleDao {

    private List<Role> parsFromResultSet(ResultSet set) throws SQLException {
        List<Role> result = new ArrayList<>();
        if(set==null) return result;
        while (set.next()){
            result.add(new Role(set.getInt(RoleMapping.ID),set.getString(RoleMapping.NAME)));
        }
        return result;
    }

    private boolean isRowExist(Role entity,Connection connection)
            throws SQLException {

        String selectSql = "SELECT * FROM " + RoleMapping.TABLE_NAME
                + " WHERE " + RoleMapping.NAME + " = ?;";

        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setString(1,entity.getName());
        ResultSet resultSet = statement.executeQuery();

        boolean result = resultSet.next();
        resultSet.close();
        statement.close();

        return  result;
    }

    @Override
    public Role selectEntityById(Integer id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Role> result;

        try {
            String sql = "SELECT * FROM " + RoleMapping.TABLE_NAME
                    + " WHERE " + RoleMapping.ID + " = ?;";
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
    public boolean update(Role entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result = 0;
        try {
            if(isRowExist(entity,connection))return false;

            String updateSql = "UPDATE " + RoleMapping.TABLE_NAME
                    + " SET " + RoleMapping.NAME + " = ? "
                    + "WHERE " + RoleMapping.ID +" = ?;";
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
            String sql = "DELETE FROM " + RoleMapping.TABLE_NAME
                    + " WHERE " + RoleMapping.ID +" = ?;";
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
    public boolean create(Role entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result = 0;
        try {
            if(isRowExist(entity,connection))return false;

            String insectSql = "INSERT INTO " + RoleMapping.TABLE_NAME
                    + " (" + RoleMapping.NAME + ")"
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

    @Override
    public List<Role> selectAll() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Role> result;

        try {
            String sql = "SELECT * FROM " + RoleMapping.TABLE_NAME + ";";
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
}
