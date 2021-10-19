package entytiesServiceTests;

import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.connectionPool.DBResourceManager;
import by.epam.jwd.testingApp.model.dataBaseMapping.StatementMapping;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractStatementService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

public class StatementServiceTest extends Assert {
    private static Statement firstStatement;
    private static Statement secondStatement;
    private static AbstractStatementService service;

    @BeforeClass
    public static void init(){
        service = EntitiesServiceFactory.getInstance().getStatementService();

        firstStatement = new Statement();
        firstStatement.setCorrect(false);
        firstStatement.setId(1);
        firstStatement.setQuestionId(1);
        firstStatement.setText("1");

        secondStatement = new Statement();
        secondStatement.setCorrect(false);
        secondStatement.setId(2);
        secondStatement.setQuestionId(1);
        secondStatement.setText("2");

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
        connection.createStatement().executeUpdate("TRUNCATE TABLE " + StatementMapping.TABLE_NAME);
        ConnectionPool.getInstance().returnConnection(connection);
    }

    @Test
    public void createStatement() throws ServiceException {
        assertTrue(service.create(firstStatement));
    }

    @Test
    public void createNullStatement() throws ServiceException {
        assertFalse(service.create(null));
    }

    @Test
    public void selectStatement() throws ServiceException {
        service.create(firstStatement);
        assertEquals(firstStatement.getId(), service.selectEntityById(firstStatement.getId()).getId());
    }

    @Test
    public void selectNullStatement() throws ServiceException {
        assertNull(service.selectEntityById(null));
    }

    @Test
    public void updateStatement() throws ServiceException {
        service.create(firstStatement);
        firstStatement.setText("new text");
        service.update(firstStatement);
        assertEquals(firstStatement.getText(),service.selectEntityById(firstStatement.getId()).getText());
    }
    @Test
    public void updateNotExistingStatement() throws ServiceException {
        service.update(firstStatement);
        assertFalse(service.update(firstStatement));
    }

    @Test
    public void deleteStatement() throws ServiceException {
        service.create(firstStatement);
        assertTrue(service.delete(firstStatement.getId()));
    }

    @Test
    public void deleteNotExistingStatement() throws ServiceException {
        service.create(firstStatement);
        assertFalse(service.delete(firstStatement.getId() + 1));
    }

    @Test
    public void deleteNullStatement() throws ServiceException {
        assertFalse(service.delete(null));
    }

    @Test
    public void deleteAllStatementsByQuestionId() throws ServiceException {
        service.create(firstStatement);
        service.create(secondStatement);
        service.deleteAllByQuestionId(firstStatement.getQuestionId());
        assertEquals(0,service.selectByQuestionId(firstStatement.getId()).size());
    }

    @Test
    public void deleteStatementsByQuestionId() throws ServiceException {
        service.create(firstStatement);

        Statement newStatement = new Statement();
        newStatement.setCorrect(false);
        newStatement.setId(firstStatement.getId() + 1);
        newStatement.setText("1");
        newStatement.setQuestionId(firstStatement.getQuestionId() + 1);

        service.create(newStatement);
        service.deleteAllByQuestionId(firstStatement.getQuestionId());
        assertEquals(1,service.selectByQuestionId(newStatement.getId()).size());
    }

}
