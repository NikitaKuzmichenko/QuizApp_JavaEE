package by.epam.jwd.testingApp.service;

import by.epam.jwd.testingApp.entities.Statement;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;

import java.util.List;

public class StatementService {

    public static boolean createStatement(Statement entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean updateStatement(Statement entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean deleteStatement(int entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static Statement selectStatementById(int statementId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    selectEntityById(statementId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static List<Statement> selectStatementByQuestionId(int questionId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    selectByQuestionId(questionId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
