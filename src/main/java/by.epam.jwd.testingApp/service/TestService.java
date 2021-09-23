package by.epam.jwd.testingApp.service;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.abstractDao.entitiesDao.AbstractTestDao;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.model.dataBaseMapping.ResultMapping;
import by.epam.jwd.testingApp.model.dataBaseMapping.TestMapping;

import java.util.List;

public class TestService {

    public static final int LimitOnPage = 5;

    public static final String sortByUsersNumber = "( SELECT COUNT(" + ResultMapping.TEST_ID + ")"
            + " FROM " + ResultMapping.TABLE_NAME
                    + " WHERE " + ResultMapping.TEST_ID + " = " + TestMapping.ID + ")";


    public static List<Test> selectTestsByCreatorId(int creatorId, int offset)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao()
                    .selectTestsByIntRow(LimitOnPage,offset,TestMapping.CREATOR_ID,creatorId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static List<Test> selectTestsByCategory(int categoryId, int offset)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao()
                    .selectTestsByIntRow(LimitOnPage,offset,TestMapping.CATEGORY_ID,categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static List<Test> sortTestsByUsersPassedNumber(int offset, boolean desc)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    sortTestsByRow(LimitOnPage,offset,desc,sortByUsersNumber);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static List<Test> sortTestsByName(int offset, boolean desc)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    sortTestsByRow(LimitOnPage,offset,desc,TestMapping.NAME);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static List<Test> sortTestsByCreationDate(int offset, boolean desc)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    sortTestsByRow(LimitOnPage,offset,desc,TestMapping.CREATION_DATE);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean createTest(Test entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean updateTest(Test entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean deleteTest(int entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static Test selectTestById(int entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDao().
                    selectEntityById(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    public static boolean removeTest(int entityId) throws ServiceException {
        AbstractTestDao testDao = DaoFactory.getInstance().getTestDao();
        try {
            return DaoFactory.getInstance().getTestDao().removeTest(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

}
