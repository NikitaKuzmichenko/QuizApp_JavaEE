package by.epam.jwd.testingApp.model.dao.factory;

import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractCategoryDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractCommentDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractQuestionDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractResultDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractRoleDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractStatementDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractTestDao;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractUserDao;
import by.epam.jwd.testingApp.model.dao.jdbcDao.CategoryDaoJDBC;
import by.epam.jwd.testingApp.model.dao.jdbcDao.CommentDaoJDBC;
import by.epam.jwd.testingApp.model.dao.jdbcDao.QuestionDaoJDBC;
import by.epam.jwd.testingApp.model.dao.jdbcDao.ResultDaoJDBC;
import by.epam.jwd.testingApp.model.dao.jdbcDao.RoleDaoJDBC;
import by.epam.jwd.testingApp.model.dao.jdbcDao.StatementDaoJDBC;
import by.epam.jwd.testingApp.model.dao.jdbcDao.TestDaoJDBC;
import by.epam.jwd.testingApp.model.dao.jdbcDao.UserDaoJDBC;

public class DaoFactory {
    private static DaoFactory instance = null;

    private final AbstractCategoryDao categoryDao = new CategoryDaoJDBC();
    private final AbstractCommentDao commentDao = new CommentDaoJDBC();
    private final AbstractQuestionDao questionDao = new QuestionDaoJDBC();
    private final AbstractResultDao resultDao = new ResultDaoJDBC();
    private final AbstractRoleDao roleDao = new RoleDaoJDBC();
    private final AbstractStatementDao statementDao = new StatementDaoJDBC();
    private final AbstractTestDao testDao = new TestDaoJDBC();
    private final AbstractUserDao userDao = new UserDaoJDBC();

    private DaoFactory(){}

    public static DaoFactory getInstance() {
        if(instance==null){
            instance = new DaoFactory();
        }
        return instance;
    }

    public AbstractCategoryDao getCategoryDao() {
        return categoryDao;
    }

    public AbstractCommentDao getCommentDao() {
        return commentDao;
    }

    public AbstractQuestionDao getQuestionDao() {
        return questionDao;
    }

    public AbstractResultDao getResultDao() {
        return resultDao;
    }

    public AbstractRoleDao getRoleDao() {
        return roleDao;
    }

    public AbstractStatementDao getStatementDao() {
        return statementDao;
    }

    public AbstractTestDao getTestDao() {
        return testDao;
    }

    public AbstractUserDao getUserDao() {
        return userDao;
    }
}
