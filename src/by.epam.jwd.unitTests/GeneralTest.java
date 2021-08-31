
import by.epam.jwd.testingApp.entities.Category;
import by.epam.jwd.testingApp.entities.Pair;
import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractResultDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractTestDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractUserDao;
import by.epam.jwd.testingApp.model.dao.jdbcDao.ResultDaoJDBC;
import by.epam.jwd.testingApp.model.dao.jdbcDao.TestDaoJDBC;
import by.epam.jwd.testingApp.model.dao.jdbcDao.UserDaoJDBC;

import java.sql.*;
import java.util.Date;

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
