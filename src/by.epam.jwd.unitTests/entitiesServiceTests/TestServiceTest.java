package entitiesServiceTests;

import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.connectionPool.DBResourceManager;
import by.epam.jwd.testingApp.model.dataBaseMapping.TestMapping;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class TestServiceTest extends Assert {

    private static Test firstTest;
    private static Test notAvailableTest;
    private static Test removedTest;
    private static AbstractTestService service;

    @BeforeClass
    public static void init(){
        service = EntitiesServiceFactory.getInstance().getTestService();

        firstTest = new Test();
        firstTest.setCreatorId(1);
        firstTest.setAvailable(true);
        firstTest.setCategoryId(1);
        firstTest.setName("test");
        firstTest.setId(1);
        firstTest.setCreationDate(new Date());
        firstTest.setRemoved(false);

        notAvailableTest = new Test();
        notAvailableTest.setCreatorId(2);
        notAvailableTest.setAvailable(false);
        notAvailableTest.setCategoryId(2);
        notAvailableTest.setName("test2");
        notAvailableTest.setId(2);
        notAvailableTest.setCreationDate(new Date());
        notAvailableTest.setRemoved(false);

        removedTest = new Test();
        removedTest.setCreatorId(3);
        removedTest.setAvailable(true);
        removedTest.setCategoryId(3);
        removedTest.setName("test3");
        removedTest.setId(3);
        removedTest.setCreationDate(new Date());
        removedTest.setRemoved(true);

        DBResourceManager.setBundleName("testDataBase");
        ConnectionPool.getInstance().InitPool();
    }

    @AfterClass
    public static void destroy(){
        ConnectionPool.getInstance().removeAllConnections();
    }

    @After
    public void cleanUpTable() throws SQLException {
        Connection connection= ConnectionPool.getInstance().takeConnection();

        connection.createStatement().executeUpdate("TRUNCATE TABLE " + TestMapping.TABLE_NAME);
        ConnectionPool.getInstance().returnConnection(connection);
    }

    @org.junit.Test
    public void creteTest() throws ServiceException {
        assertTrue(service.create(firstTest));
    }

    @org.junit.Test
    public void creteNullTest() throws ServiceException {
        assertFalse(service.create(null));
    }

    @org.junit.Test
    public void creteTestAndGetId() throws ServiceException {
        assertEquals((Integer) 1,service.createAndGetId(firstTest));
    }

    @org.junit.Test
    public void creteNullTestAndGetId() throws ServiceException {
        assertNull(service.createAndGetId(null));
    }

    @org.junit.Test
    public void selectByIdTest() throws ServiceException {
        service.create(firstTest);
        assertEquals(firstTest.getName(),service.selectEntityById(firstTest.getId()).getName());
    }

    @org.junit.Test
    public void selectByNotExistingIdTest() throws ServiceException {
        service.create(firstTest);
        assertNull(service.selectEntityById(firstTest.getId() + 1));
    }

    @org.junit.Test
    public void selectByNullIdTest() throws ServiceException {
        assertNull(service.selectEntityById(null));
    }

    @org.junit.Test
    public void updateTest() throws ServiceException {
        service.create(firstTest);
        firstTest.setName("newName");
        service.update(firstTest);

        assertEquals(firstTest.getId(),service.selectEntityById(firstTest.getId()).getId());
    }

    @org.junit.Test
    public void deleteTest() throws ServiceException {
        service.create(firstTest);
        assertTrue(service.delete(firstTest.getId()));
    }

    @org.junit.Test
    public void deleteNotExistingTest() throws ServiceException {
        assertFalse(service.delete(firstTest.getId()));
    }

    @org.junit.Test
    public void removeTest() throws ServiceException {
        service.create(removedTest);
        service.remove(1);
        assertTrue(service.selectEntityById(1).isRemoved());
    }

    @org.junit.Test
    public void removeNullTest() throws ServiceException {
        assertFalse(service.remove(null));
    }

    @org.junit.Test
    public void calculateNoCategoryTotalTestsNumber() throws ServiceException {
        service.create(firstTest);
        service.create(notAvailableTest);
        service.create(removedTest);
        assertEquals(2,service.calculateTotalTestsNumber(null));
    }

    @org.junit.Test
    public void calculateTotalTestsNumber() throws ServiceException {
        service.create(firstTest);
        service.create(notAvailableTest);
        assertEquals(1,service.calculateTotalTestsNumber(firstTest.getCategoryId()));
    }

    @org.junit.Test
    public void calculateTotalTestsNumberCreatedByUser() throws ServiceException {
        service.create(firstTest);
        service.create(notAvailableTest);
        assertEquals(1,service.calculateUsersTotalTestsNumber(firstTest.getCreatorId(),false));
    }

    @org.junit.Test
    public void calculateTotalAvailableTestsNumberCreatedByUser() throws ServiceException {
        service.create(firstTest);
        service.create(notAvailableTest);
        assertEquals(1,service.calculateUsersTotalTestsNumber(firstTest.getCreatorId(),false));
    }

    @org.junit.Test
    public void selectTestsByCategory() throws ServiceException {
        service.create(firstTest);
        assertEquals(firstTest.getName(),
                service.selectByCategory(firstTest.getCreatorId(),0,true,1).get(0).getName());
    }

    @org.junit.Test
    public void selectNotAvailableTestsByCategory() throws ServiceException {
        service.create(notAvailableTest);
        assertEquals(0,
                service.selectByCategory(firstTest.getCreatorId(),0,true,1).size());
    }

    @org.junit.Test
    public void selectTestsByCreator() throws ServiceException {
        service.create(firstTest);
        assertEquals(firstTest.getName(),
                service.selectByCategory(firstTest.getCreatorId(),0,true,1).get(0).getName());
    }

    @org.junit.Test
    public void selectNotAvailableTestsByCreator() throws ServiceException {
        service.create(notAvailableTest);
        assertEquals(0,
                service.selectByCreatorId(firstTest.getCreatorId(),0,true,1).size());
    }

    @org.junit.Test
    public void sortTestsByCreationDateDown() throws ServiceException {
        Date date = new Date();
        date.setTime(firstTest.getCreationDate().getTime()*2);

        Test secondTest = new Test();
        secondTest.setCreatorId(2);
        secondTest.setAvailable(true);
        secondTest.setCategoryId(2);
        secondTest.setName("test4");
        secondTest.setId(2);
        secondTest.setCreationDate(date);
        secondTest.setRemoved(false);


        service.create(firstTest);
        service.create(secondTest);
        service.create(removedTest);

        assertEquals(secondTest.getId(), service.sortByCreationDate(0, true, 1).get(0).getId());
    }

    @org.junit.Test
    public void sortTestsByCreationDateUp() throws ServiceException {
        Date date = new Date();
        date.setTime(firstTest.getCreationDate().getTime()*2);

        Test secondTest = new Test();
        secondTest.setCreatorId(2);
        secondTest.setAvailable(true);
        secondTest.setCategoryId(2);
        secondTest.setName("test4");
        secondTest.setId(2);
        secondTest.setCreationDate(date);
        secondTest.setRemoved(false);

        service.create(firstTest);
        service.create(secondTest);
        service.create(removedTest);

        assertEquals(firstTest.getId(), service.sortByCreationDate(0, false, 1).get(0).getId());
    }

    @org.junit.Test
    public void sortTestsByNameDown() throws ServiceException {
        Date date = new Date();
        date.setTime(firstTest.getCreationDate().getTime()*2);

        Test secondTest = new Test();
        secondTest.setCreatorId(2);
        secondTest.setAvailable(true);
        secondTest.setCategoryId(2);
        secondTest.setName("test4");
        secondTest.setId(2);
        secondTest.setCreationDate(date);
        secondTest.setRemoved(false);

        service.create(firstTest);
        service.create(secondTest);
        service.create(removedTest);

        assertEquals(secondTest.getId(), service.sortByName(0, true, 1).get(0).getId());
    }

    @org.junit.Test
    public void sortTestsByNameUp() throws ServiceException {
        Date date = new Date();
        date.setTime(firstTest.getCreationDate().getTime()*2);

        Test secondTest = new Test();
        secondTest.setCreatorId(2);
        secondTest.setAvailable(true);
        secondTest.setCategoryId(2);
        secondTest.setName("test4");
        secondTest.setId(2);
        secondTest.setCreationDate(date);
        secondTest.setRemoved(false);

        service.create(firstTest);
        service.create(secondTest);
        service.create(removedTest);

        assertEquals(firstTest.getId(), service.sortByName(0, false, 1).get(0).getId());
    }
}
