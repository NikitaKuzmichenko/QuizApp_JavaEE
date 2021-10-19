package entytiesServiceTests;

import by.epam.jwd.testingApp.entity.Role;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.connectionPool.DBResourceManager;
import by.epam.jwd.testingApp.model.dataBaseMapping.RoleMapping;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractRoleService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

public class RoleServiceTest extends Assert {
    private static Role role;
    private static AbstractRoleService service;

    @BeforeClass
    public static void init(){
        service = EntitiesServiceFactory.getInstance().getRoleService();

        role = new Role();
        role.setId(1);
        role.setName("role");

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
        connection.createStatement().executeUpdate("TRUNCATE TABLE " + RoleMapping.TABLE_NAME);
        ConnectionPool.getInstance().returnConnection(connection);
    }

    @Test
    public void createRole() throws ServiceException {
        assertTrue(service.create(role));
    }

    @Test
    public void createNullRole() throws ServiceException {
        assertFalse(service.create(null));
    }

    @Test
    public void selectRole() throws ServiceException {
        service.create(role);
        assertEquals(role.getId(), service.selectEntityById(role.getId()).getId());
    }

    @Test
    public void selectNullRole() throws ServiceException {
        assertNull(service.selectEntityById(null));
    }

    @Test
    public void updateRole() throws ServiceException {
        service.create(role);
        role.setName("new name");
        service.update(role);
        assertEquals(role.getId(),service.selectEntityById(role.getId()).getId());
    }
    @Test
    public void updateNotExistingRole() throws ServiceException {
        service.update(role);
        assertFalse(service.update(role));
    }

    @Test
    public void deleteRole() throws ServiceException {
        service.create(role);
        assertTrue(service.delete(role.getId()));
    }

    @Test
    public void deleteNotExistingRole() throws ServiceException {
        service.create(role);
        assertFalse(service.delete(role.getId() + 1));
    }

    @Test
    public void deleteNullRole() throws ServiceException {
        assertFalse(service.delete(null));
    }

    @Test
    public void selectAllRoles() throws ServiceException {
        service.create(role);
        assertEquals(1,service.selectAll().size());
    }
}
