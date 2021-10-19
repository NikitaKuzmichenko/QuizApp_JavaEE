package entitiesServiceTests;

import by.epam.jwd.testingApp.entity.Pair;
import by.epam.jwd.testingApp.entity.Result;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.connectionPool.DBResourceManager;
import by.epam.jwd.testingApp.model.dataBaseMapping.ResultMapping;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractResultService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class ResultServiceTest extends Assert {
    private static Pair<Integer,Integer> resultId;
    private static Result firstResult;
    private static Result secondResult;
    private static AbstractResultService service;

    @BeforeClass
    public static void init(){
        service = EntitiesServiceFactory.getInstance().getResultService();

        resultId = new Pair<>(1,1);

        firstResult = new Result();
        firstResult.setUserId(resultId.getT());
        firstResult.setTestId(resultId.getU());
        firstResult.setPassingDate(new Date());
        firstResult.setResult(100);

        secondResult = new Result();
        secondResult.setUserId(resultId.getT());
        secondResult.setTestId(resultId.getU() + 1);
        secondResult.setPassingDate(new Date());
        secondResult.setResult(200);

        DBResourceManager.setBundleName("testDataBase");
        ConnectionPool.getInstance().InitPool();
    }

    @AfterClass
    public static void destroy(){
        ConnectionPool.getInstance().removeAllConnections();
    }

    @After
    public void cleanUpTable() throws SQLException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        connection.createStatement().executeUpdate("TRUNCATE TABLE " + ResultMapping.TABLE_NAME);
        ConnectionPool.getInstance().returnConnection(connection);
    }

    @Test
    public void createRole() throws ServiceException {
        assertTrue(service.create(firstResult));
    }

    @Test
    public void createNullRole() throws ServiceException {
        assertFalse(service.create(null));
    }

    @Test
    public void selectRole() throws ServiceException {
        service.create(firstResult);
        assertEquals(firstResult.getUserId(), service.selectEntityById(resultId).getUserId());
    }

    @Test
    public void selectNullRole() throws ServiceException {
        assertNull(service.selectEntityById(null));
    }

    @Test
    public void updateRole() throws ServiceException {
        service.create(firstResult);
        firstResult.setResult(300);
        service.update(firstResult);
        assertEquals(firstResult.getUserId(), service.selectEntityById(resultId).getUserId());
    }
    @Test
    public void updateNotExistingRole() throws ServiceException {
        service.update(firstResult);
        assertFalse(service.update(firstResult));
    }

    @Test
    public void deleteRole() throws ServiceException {
        service.create(firstResult);
        assertTrue(service.delete(resultId));
    }

    @Test
    public void deleteNotExistingRole() throws ServiceException {
        assertFalse(service.delete(resultId));
    }

    @Test
    public void deleteNullRole() throws ServiceException {
        assertFalse(service.delete(null));
    }

    @Test
    public void calculateAvgResultByTestId() throws ServiceException {
        service.create(firstResult);
        service.create(secondResult);
        assertEquals((Integer)firstResult.getResult(),service.calculateAvgResultByTestId(firstResult.getTestId()));
    }

    @Test
    public void calculateRowsNumberByUserId() throws ServiceException {
        service.create(firstResult);
        service.create(secondResult);
        assertEquals((Integer)2,service.calculateRowsNumberByUserId(firstResult.getTestId()));
    }

    @Test
    public void calculateRowsNumberByTestId() throws ServiceException {
        service.create(firstResult);
        service.create(secondResult);
        assertEquals((Integer)1,service.calculateRowsNumberByTestId(firstResult.getTestId()));
    }

    @Test
    public void calculateZeroAvgResultByTestId() throws ServiceException {
        assertEquals((Integer)0,service.calculateAvgResultByTestId(firstResult.getTestId()));
    }

    @Test
    public void calculateZeroRowsNumberByUserId() throws ServiceException {
        assertEquals((Integer)0,service.calculateRowsNumberByUserId(firstResult.getTestId()));
    }

    @Test
    public void calculateZeroRowsNumberByTestId() throws ServiceException {
        assertEquals((Integer)0,service.calculateRowsNumberByTestId(firstResult.getTestId()));
    }

}
