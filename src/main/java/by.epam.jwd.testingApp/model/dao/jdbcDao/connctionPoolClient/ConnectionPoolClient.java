package by.epam.jwd.testingApp.model.dao.jdbcDao.connctionPoolClient;

import by.epam.jwd.testingApp.exceptions.ConnectionPoolException;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;

import java.sql.Connection;

public class ConnectionPoolClient {
    ConnectionPool pool;

    public ConnectionPoolClient() throws DaoException {
        try {
            pool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection pool in " + this.getClass(),e);
        }
    }

    public Connection getConnection() throws DaoException {
        try {
            return pool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from pool in " + this.getClass(),e);
        }
    }

    public void returnConnection(Connection connection) throws DaoException {
        pool.returnConnection(connection);
    }
}
