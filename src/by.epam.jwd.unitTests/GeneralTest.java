
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractTestDao;
import by.epam.jwd.testingApp.model.dao.jdbcDao.TestDaoJDBC;

import java.sql.*;

public class GeneralTest {

/*    public static AbstractGenericDao<?,?> f(){
        return new CategoryDaoJDBC();
    }*/
    //CategoryDaoJDBC dao = (CategoryDaoJDBC) f();

    public static void main(String[] args) throws DaoException {
        ConnectionPool.getInstance().InitPool();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        AbstractTestDao cat = new TestDaoJDBC();
/*        System.out.println(cat.update(new Result(4,2,9000,new Date())));
        System.out.println(cat.selectEntityById(new Pair<>(2,4)));*/
        System.out.println(cat.sortTestsByPassedNumber( 2,0,true));

    }
}
