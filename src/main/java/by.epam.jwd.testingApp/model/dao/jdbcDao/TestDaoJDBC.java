package by.epam.jwd.testingApp.model.dao.jdbcDao;
import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractTestDao;
import by.epam.jwd.testingApp.model.dataBaseMapping.TestMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDaoJDBC implements AbstractTestDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String LAST_INSERT_ID_SQL = "SELECT LAST_INSERT_ID()";

    public static final String REMOVE_SQL = "UPDATE " + TestMapping.TABLE_NAME
            + " SET " + TestMapping.IS_REMOVED + " = ?"
            + " WHERE " + TestMapping.ID + " = ? ;";

    public static final String TEST_NUMBER_ACCUMULATOR = "result";

    public static final String SELECT_BY_ID_SQL = "SELECT * FROM " + TestMapping.TABLE_NAME
            + " WHERE " + TestMapping.ID + " = ?;";

    public static final String UPDATE_SQL = "UPDATE " + TestMapping.TABLE_NAME
            + " SET " + TestMapping.NAME + " = ?"
            + ", " + TestMapping.CREATOR_ID + " = ?"
            + ", " + TestMapping.CATEGORY_ID + " = ?"
            + ", " + TestMapping.CREATION_DATE + " = ?"
            + ", " + TestMapping.IS_REMOVED + " = ?"
            + ", " + TestMapping.IS_AVAILABLE + " = ?"
            + " WHERE " + TestMapping.ID +" = ?;";

    public static final String DELETE_SQL = "DELETE FROM " + TestMapping.TABLE_NAME
            + " WHERE " + TestMapping.ID +" = ?;";

    public static final String CREATE_SQL = "INSERT INTO " + TestMapping.TABLE_NAME
            + " (" + TestMapping.CREATOR_ID + ", "
            + TestMapping.CATEGORY_ID + ", "
            + TestMapping.NAME + ", "
            + TestMapping.IS_AVAILABLE + ", "
            + TestMapping.CREATION_DATE + ")"
            +  "VALUES(?,?,?,?,?);";

    @Override
    public List<Test> selectSortedTestsByIntRow
            (int limit, int offset, boolean desc, String sortByRow, String rowName, int rowValue,boolean onlyAvailable)
            throws DaoException {

        if(sortByRow == null || rowName == null) return null;

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
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return result;
    }

    @Override
    public List<Test> sortTestsByRow(int limit, int offset, boolean desc ,String sortedRow,boolean onlyAvailable) throws DaoException {

        if(sortedRow==null) return null;

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
            } catch (SQLException e) {LOGGER.error(e);}
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
            statement = connection.prepareStatement(REMOVE_SQL);
            statement.setInt(2,testId);
            statement.setBoolean(1,true);
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
    public int calculateTestsNumber(int rowValue,String rowName,boolean onlyAvailable,boolean onlyExisting)throws DaoException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();

        PreparedStatement statement = null;
        ResultSet resultSet;
        int result;

        try {
            StringBuilder updateSql = new StringBuilder();
            updateSql.append("SELECT COUNT(*) as " + TEST_NUMBER_ACCUMULATOR);
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
            result = resultSet.getInt(TEST_NUMBER_ACCUMULATOR);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
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
    public boolean update(Test entity) throws DaoException {
        if(entity == null) return false;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1,entity.getName());
            statement.setInt(2,entity.getCreatorId());
            statement.setInt(3,entity.getCategoryId());
            statement.setDate(4, new java.sql.Date(entity.getCreationDate().getTime()));
            statement.setBoolean(5,entity.isRemoved());
            statement.setBoolean(6,entity.isAvailable());
            statement.setInt(7,entity.getId());
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
    public boolean create(Test entity) throws DaoException {
        if(entity == null) return false;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        try {
            statement = connection.prepareStatement(CREATE_SQL);
            statement.setInt(1,entity.getCreatorId());
            statement.setInt(2,entity.getCategoryId());
            statement.setString(3,entity.getName());
            statement.setBoolean(4,entity.isAvailable());
            statement.setDate(5,new java.sql.Date(entity.getCreationDate().getTime()));
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
    public Integer createAndGetId(Test entity) throws DaoException {
        if(entity == null) return null;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.takeConnection();
        PreparedStatement statement = null;
        int result;
        Integer key = null;

        try {

            statement = connection.prepareStatement(CREATE_SQL);
            statement.setInt(1,entity.getCreatorId());
            statement.setInt(2,entity.getCategoryId());
            statement.setString(3,entity.getName());
            statement.setBoolean(4,entity.isAvailable());
            statement.setDate(5,new java.sql.Date(entity.getCreationDate().getTime()));
            result = statement.executeUpdate();

            if(result == 1){
                ResultSet set = statement.executeQuery(LAST_INSERT_ID_SQL);
                if(set.next()){
                    key = set.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {LOGGER.error(e);}
            pool.returnConnection(connection);
        }
        return key;
    }

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
}
