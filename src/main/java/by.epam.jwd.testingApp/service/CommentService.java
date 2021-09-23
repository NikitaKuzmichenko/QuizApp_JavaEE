package by.epam.jwd.testingApp.service;

import by.epam.jwd.testingApp.entities.Comment;
import by.epam.jwd.testingApp.entities.Question;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;

import java.util.List;

public class CommentService {

    public static final int LimitOnPage = 10;

    public static boolean createComment(Comment entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean updateComment(Comment entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean deleteComment(int entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static Comment selectCommentById(int statementId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    selectEntityById(statementId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static List<Comment> selectCommentsByTestId(int testId)throws ServiceException {
        try {
            return DaoFactory.getInstance().getCommentDao().
                    selectByTestId(testId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
