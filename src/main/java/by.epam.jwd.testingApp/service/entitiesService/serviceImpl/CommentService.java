package by.epam.jwd.testingApp.service.entitiesService.serviceImpl;

import by.epam.jwd.testingApp.entity.Comment;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractCommentService;

import java.util.List;

public class CommentService implements AbstractCommentService{
    @Override
    public boolean create(Comment entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean update(Comment entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean delete(Integer entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Comment selectEntityById(Integer statementId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    selectEntityById(statementId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Comment> selectEntityByTestId(Integer testId)throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    selectByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
