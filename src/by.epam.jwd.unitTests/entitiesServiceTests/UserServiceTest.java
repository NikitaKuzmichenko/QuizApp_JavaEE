package entitiesServiceTests;

import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.connectionPool.DBResourceManager;
import by.epam.jwd.testingApp.model.dataBaseMapping.UserMapping;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractUserService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceTest extends Assert {

    private static User firstUser;
    private static AbstractUserService service;

    @BeforeClass
    public static void init(){
        service = EntitiesServiceFactory.getInstance().getUserService();
        firstUser = new User();
        firstUser.setId(1);
        firstUser.setEmail("email");
        firstUser.setName("name");
        firstUser.setPassword("password");
        firstUser.setRoleId(1);

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
        connection.createStatement().executeUpdate("TRUNCATE TABLE " + UserMapping.TABLE_NAME);
        ConnectionPool.getInstance().returnConnection(connection);
    }

    @Test
    public void addUserTest() throws ServiceException {
        assertTrue(service.create(firstUser));
    }

    @Test
    public void addSameUserTest() throws ServiceException {
        service.create(firstUser);
        assertFalse(service.create(firstUser));
    }

    @Test
    public void addNullUserTest() throws ServiceException {
        assertFalse(service.create(null));
    }

    @Test
    public void selectUserByIdTest() throws ServiceException {
        service.create(firstUser);
        assertEquals(firstUser, service.selectEntityById(firstUser.getId()));
    }

    @Test
    public void selectUserByEmailTest() throws ServiceException {
        service.create(firstUser);
        assertEquals(firstUser, service.selectByEmail(firstUser.getEmail()));
    }

    @Test
    public void updateUserTest() throws ServiceException {
        User updatedUser  = new User();
        updatedUser.setId(firstUser.getId());
        updatedUser.setName("name1");
        updatedUser.setRoleId(firstUser.getRoleId());
        updatedUser.setPassword(firstUser.getPassword());
        updatedUser.setEmail(firstUser.getEmail());
        service.create(firstUser);
        service.update(updatedUser);
        assertEquals(firstUser, service.selectEntityById(firstUser.getId()));
    }

    @Test
    public void selectTestCreatorsTest() throws ServiceException {
        service.create(firstUser);
        List<by.epam.jwd.testingApp.entity.Test> tests = new ArrayList<>();
        by.epam.jwd.testingApp.entity.Test test = new by.epam.jwd.testingApp.entity.Test();
        tests.add(test);
        test.setCreatorId(firstUser.getId());
        assertEquals(firstUser,service.selectTestCreators(tests).get(0));
    }

    @Test
    public void selectNullTestCreatorsTest() throws ServiceException {
        assertNull(service.selectTestCreators(null));
    }

    @Test
    public void deleteUserTest() throws ServiceException {
        service.create(firstUser);
        assertTrue(service.delete(firstUser.getId()));
    }

    @Test
    public void deleteNotExistingUserTest() throws ServiceException {
        assertFalse(service.delete(firstUser.getId()));
    }

}
