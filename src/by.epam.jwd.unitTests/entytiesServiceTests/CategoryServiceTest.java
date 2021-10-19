package entytiesServiceTests;

import by.epam.jwd.testingApp.entity.Category;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.connectionPool.DBResourceManager;
import by.epam.jwd.testingApp.model.dataBaseMapping.CategoryMapping;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractCategoryService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

public class CategoryServiceTest extends Assert {
    private static Category category;
    private static AbstractCategoryService service;

    @BeforeClass
    public static void init(){
        service = EntitiesServiceFactory.getInstance().getCategoryService();

        category = new Category();
        category.setId(1);
        category.setName("role");

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
        connection.createStatement().executeUpdate("TRUNCATE TABLE " + CategoryMapping.TABLE_NAME);
        ConnectionPool.getInstance().returnConnection(connection);
    }

    @Test
    public void createRole() throws ServiceException {
        assertTrue(service.create(category));
    }

    @Test
    public void createNullRole() throws ServiceException {
        assertFalse(service.create(null));
    }


    @Test
    public void selectRole() throws ServiceException {
        service.create(category);
        assertEquals(category.getId(), service.selectEntityById(category.getId()).getId());
    }

    @Test
    public void selectNullRole() throws ServiceException {
        assertNull(service.selectEntityById(null));
    }

    @Test
    public void updateRole() throws ServiceException {
        service.create(category);
        category.setName("new name");
        service.update(category);
        assertEquals(category.getId(),service.selectEntityById(category.getId()).getId());
    }
    @Test
    public void updateNotExistingRole() throws ServiceException {
        service.update(category);
        assertFalse(service.update(category));
    }

    @Test
    public void deleteRole() throws ServiceException {
        service.create(category);
        assertTrue(service.delete(category.getId()));
    }

    @Test
    public void deleteNotExistingRole() throws ServiceException {
        service.create(category);
        assertFalse(service.delete(category.getId() + 1));
    }

    @Test
    public void deleteNullRole() throws ServiceException {
        assertFalse(service.delete(null));
    }

    @Test
    public void selectAllRoles() throws ServiceException {
        service.create(category);
        assertEquals(1,service.selectAll().size());
    }
}
