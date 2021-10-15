package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractTestDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.TestMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDaoJDBC implements AbstractTestDao {

    private List<Test> parsFromResultSet(ResultSet set) throws SQLException {
        List<Test> result = new ArrayList<>();
        if(set==null) return result;

        while (set.next()){
            result.add(new Test(
                    set.getInt(TestMapping.ID),
                    set.getInt(TestMapping.CREATOR_ID),
                    set.getInt(TestMapping.CATEGORY_ID),
                    set.getString(TestMapping.NAME),
                    set.getDate(TestMapping.CREATION_DATE),
                    set.getBoolean(TestMapping.IS_REMOVED),
                    set.getBoolean(TestMapping.IS_AVAILABLE))
            );
        }
        return result;
    }

    @Override
    public List<Test> selectSortedTestsByIntRow
            (int limit, int offset, boolean desc, String sortByRow, String rowName, int rowValue,boolean onlyAvailable)
            throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Test> result;

        String sortType;
        if(desc) {
            sortType = " DESC";
        }
        else {
            sortType = " ASC";
        }

        String available;
        if(onlyAvailable){
            available = " AND " + TestMapping.IS_AVAILABLE + " = 1 ";
        }
        else{
            available = "";
        }

        try {
            String sql = "SELECT * FROM " + TestMapping.TABLE_NAME
                    + " WHERE " + rowName + " = ?"
                    + " AND " + TestMapping.IS_REMOVED + " = 0 "
                    + available
                    + " ORDER BY " + sortByRow + sortType
                    + " LIMIT " + limit
                    + " OFFSET " + offset + ";";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,rowValue);
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
    public List<Test> sortTestsByRow(int limit, int offset, boolean desc ,String sortedRow,boolean onlyAvailable) throws DaoException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Test> result;

        String sortType;
        if(desc) {
            sortType = " DESC";
        }
        else {
            sortType = " ASC";
        }

        String available;
        if(onlyAvailable){
            available = " AND " + TestMapping.IS_AVAILABLE + " = 1 ";
        }
        else{
            available = "";
        }

        try {
            String sql = "SELECT * FROM " + TestMapping.TABLE_NAME
                    + " WHERE " + TestMapping.IS_REMOVED + " = 0 "
                    + available
                    + " ORDER BY " + sortedRow + sortType
                    + " LIMIT " + limit
                    + " OFFSET " + offset + ";";
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
    public boolean removeTest(int testId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            String updateSql = "UPDATE " + TestMapping.TABLE_NAME
                    + " SET " + TestMapping.IS_REMOVED + " = ?"
                    + " WHERE " + TestMapping.ID + " = ? ;";
            statement = connection.prepareStatement(updateSql);
            statement.setInt(2,testId);
            statement.setBoolean(1,true);
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
    public int calculateTestsNumber(int rowValue,String rowName,boolean onlyAvailable,boolean onlyExisting)throws DaoException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        String resultColumnName = "result";
        ResultSet resultSet;
        int result;

        try {
            StringBuilder updateSql = new StringBuilder();
            updateSql.append("SELECT COUNT(*) as " + resultColumnName);
            updateSql.append(" FROM " + TestMapping.TABLE_NAME);
            updateSql.append(" WHERE ");
            if(rowName!=null) {
                updateSql.append(rowName).append(" = ?");
                if(onlyAvailable || onlyExisting){
                    updateSql.append(" AND ");
                }
            }

            if(onlyExisting){
                updateSql.append(TestMapping.IS_REMOVED);
                updateSql.append(" = 0");
                if(onlyAvailable){
                    updateSql.append(" AND ");
                }
            }

            if(onlyAvailable){
                updateSql.append(TestMapping.IS_AVAILABLE);
                updateSql.append(" = 1");
            }
            updateSql.append(";");

            statement = connection.prepareStatement(updateSql.toString());
            if(rowName!=null) {
                statement.setInt(1, rowValue);
            }
            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(resultColumnName);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {/* write in logs*/}
            pool.returnConnection(connection);
        }
        return result;
    }

    @Override
    public Test selectEntityById(Integer id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Test> result;

        try {
            String sql = "SELECT * FROM " + TestMapping.TABLE_NAME
                    + " WHERE " + TestMapping.ID + " = ?;";
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
    public boolean update(Test entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            String updateSql = "UPDATE " + TestMapping.TABLE_NAME
                    + " SET " + TestMapping.NAME + " = ?"
                    + ", " + TestMapping.CREATOR_ID + " = ?"
                    + ", " + TestMapping.CATEGORY_ID + " = ?"
                    + ", " + TestMapping.CREATION_DATE + " = ?"
                    + ", " + TestMapping.IS_REMOVED + " = ?"
                    + ", " + TestMapping.IS_AVAILABLE + " = ?"
                    + " WHERE " + TestMapping.ID +" = ?;";
            statement = connection.prepareStatement(updateSql);
            statement.setString(1,entity.getName());
            statement.setInt(2,entity.getCreatorId());
            statement.setInt(3,entity.getCategoryId());
            statement.setDate(4, (Date) entity.getCreationDate());
            statement.setBoolean(5,entity.isRemoved());
            statement.setBoolean(6,entity.isAvailable());
            statement.setInt(7,entity.getId());
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
            String sql = "DELETE FROM " + TestMapping.TABLE_NAME
                    + " WHERE " + TestMapping.ID +" = ?;";
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
    public boolean create(Test entity) throws DaoException {
        if(entity == null) return false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {

            String insectSql = "INSERT INTO " + TestMapping.TABLE_NAME
                    + " (" + TestMapping.CREATOR_ID + ", "
                    + TestMapping.CATEGORY_ID + ", "
                    + TestMapping.NAME + ", "
                    + TestMapping.CREATION_DATE + ")"
                    +  "VALUES(?,?,?,?);";

            statement = connection.prepareStatement(insectSql);
            statement.setInt(1,entity.getCreatorId());
            statement.setInt(2,entity.getCategoryId());
            statement.setString(3,entity.getName());
            statement.setDate(4,new java.sql.Date(entity.getCreationDate().getTime()));
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
    public Integer createAndGetId(Test entity) throws DaoException {
        if(entity == null) return null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        Integer key = null;

        try {

            String insectSql = "INSERT INTO " + TestMapping.TABLE_NAME
                    + " (" + TestMapping.CREATOR_ID + ", "
                    + TestMapping.CATEGORY_ID + ", "
                    + TestMapping.NAME + ", "
                    + TestMapping.CREATION_DATE + ")"
                    +  "VALUES(?,?,?,?);";

            statement = connection.prepareStatement(insectSql);
            statement.setInt(1,entity.getCreatorId());
            statement.setInt(2,entity.getCategoryId());
            statement.setString(3,entity.getName());
            statement.setDate(4,new java.sql.Date(entity.getCreationDate().getTime()));
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
