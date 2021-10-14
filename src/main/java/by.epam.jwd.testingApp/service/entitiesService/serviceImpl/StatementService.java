package by.epam.jwd.testingApp.service.entitiesService.serviceImpl;

import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractStatementService;

import java.util.List;

public class StatementService implements AbstractStatementService {

    @Override
    public boolean create(Statement entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean update(Statement entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean delete(Integer entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean deleteAllByQuestionId(Integer questionId) throws ServiceException{
        try {
            return DaoFactory.getInstance().getStatementDao().
                    deleteAllByQuestionId(questionId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Statement selectEntityById(Integer statementId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    selectEntityById(statementId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public  List<Statement> selectByQuestionId(Integer questionId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getStatementDao().
                    selectByQuestionId(questionId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
