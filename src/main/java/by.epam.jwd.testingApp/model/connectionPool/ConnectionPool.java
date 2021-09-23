package by.epam.jwd.testingApp.model.connectionPool;

import by.epam.jwd.testingApp.exceptions.ConnectionPoolException;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static volatile ConnectionPool instance;

    private String driver;
    private String password;
    private String url;
    private String user;

    public static final int DEFAULT_POOL_SIZE = 12;

    private BlockingQueue<Connection> freePool;
    private BlockingQueue<Connection> occupiedPool;

    private ConnectionPool() {
        DBResourceManager resourceManager = DBResourceManager.newInstance();
        driver = resourceManager.getValueByName(DataBaseParams.DB_DRIVER);
        password = resourceManager.getValueByName(DataBaseParams.DB_PASSWORD);
        url = resourceManager.getValueByName(DataBaseParams.DB_URL);
        user = resourceManager.getValueByName(DataBaseParams.DB_USER);
    }

    public static ConnectionPool getInstance(){
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    public void InitPool() throws ConnectionPoolException {
        freePool = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE,true);
        occupiedPool = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE,true);
        try {
            Class.forName(driver);
            for(int i=0;i<DEFAULT_POOL_SIZE;i++) {
                Connection connection;
                connection = DriverManager.getConnection(url, user, password);
                freePool.offer(connection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection pool initialization. " +
                    "Can't create connection",e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Connection pool initialization. " +
                    "Can't found driver class",e);
        }
    }

    public void removeAllConnections(){
        freePool.clear();
        occupiedPool.clear();
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = freePool.take();
            occupiedPool.offer(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Connection pool. " +
                    "Can't connect to a pool",e);
        }
        return connection;
    }

    public void returnConnection(Connection connection){
        occupiedPool.remove(connection);
        freePool.offer(connection);
    }

    public void returnConnection(Connection connection,Statement statement) throws SQLException{

        returnConnection(connection);
    }

    public void returnConnection(Connection connection,Statement statement,ResultSet set)throws SQLException{

        returnConnection(connection,statement);
    }


}
