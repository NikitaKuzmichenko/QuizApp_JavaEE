package entitiesServiceTests;

import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;
import by.epam.jwd.testingApp.model.connectionPool.DBResourceManager;
import by.epam.jwd.testingApp.model.dataBaseMapping.QuestionMapping;
import by.epam.jwd.testingApp.service.entity.abstractService.AbstractQuestionService;
import by.epam.jwd.testingApp.service.entity.factory.EntitiesServiceFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

public class QuestionServiceTest extends Assert {
    private static Question question;
    private static AbstractQuestionService service;

    @BeforeClass
    public static void init(){
        service = EntitiesServiceFactory.getInstance().getQuestionService();

        question = new Question();
        question.setId(1);
        question.setTestId(1);
        question.setTitle("role");

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
        connection.createStatement().executeUpdate("TRUNCATE TABLE " + QuestionMapping.TABLE_NAME);
        ConnectionPool.getInstance().returnConnection(connection);
    }

    @Test
    public void createQuestion() throws ServiceException {
        assertTrue(service.create(question));
    }

    @Test
    public void createNullQuestion() throws ServiceException {
        assertFalse(service.create(null));
    }

    @org.junit.Test
    public void creteQuestionAndGetId() throws ServiceException {
        assertEquals((Integer) 1,service.createAndGetId(question));
    }

    @Test
    public void selectQuestion() throws ServiceException {
        service.create(question);
        assertEquals(question.getId(), service.selectEntityById(question.getId()).getId());
    }

    @Test
    public void selectNullQuestion() throws ServiceException {
        assertNull(service.selectEntityById(null));
    }

    @Test
    public void updateQuestion() throws ServiceException {
        service.create(question);
        question.setTitle("new name");
        service.update(question);
        assertEquals(question.getId(),service.selectEntityById(question.getId()).getId());
    }
    @Test
    public void updateNotExistingQuestion() throws ServiceException {
        service.update(question);
        assertFalse(service.update(question));
    }

    @Test
    public void deleteQuestion() throws ServiceException {
        service.create(question);
        assertTrue(service.delete(question.getId()));
    }

    @Test
    public void deleteNotExistingQuestion() throws ServiceException {
        service.create(question);
        assertFalse(service.delete(question.getId() + 1));
    }

    @Test
    public void deleteNullQuestion() throws ServiceException {
        assertFalse(service.delete(null));
    }

    @Test
    public void selectQuestionsByTestId() throws ServiceException {
        service.create(question);
        assertEquals(question.getId(),service.selectEntityByTestId(question.getTestId()).get(0).getId());
    }

    @Test
    public void selectNoQuestionsByTestId() throws ServiceException {
        service.create(question);
        assertEquals(0,service.selectEntityByTestId(question.getTestId() + 1).size());
    }

    @Test
    public void calculateQuestionsNumberPerTest() throws ServiceException {
        service.create(question);
        assertEquals(1,service.calculateQuestionNumber(question.getTestId()));
    }

    @Test
    public void calculateQuestionsZeroNumberPerTest() throws ServiceException {
        assertEquals(0,service.calculateQuestionNumber(question.getTestId()));
    }

}
