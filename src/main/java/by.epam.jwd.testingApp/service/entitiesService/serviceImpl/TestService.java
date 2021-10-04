package by.epam.jwd.testingApp.service.entitiesService.serviceImpl;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.DaoException;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.model.dao.factory.DaoFactory;
import by.epam.jwd.testingApp.model.dataBaseMapping.ResultMapping;
import by.epam.jwd.testingApp.model.dataBaseMapping.TestMapping;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractTestService;

import java.util.List;

public class TestService implements AbstractTestService {

    public static final String SORT_BY_USERS_NUMBER = "( SELECT COUNT(" + ResultMapping.TEST_ID + ")"
            + " FROM " + ResultMapping.TABLE_NAME
                    + " WHERE " + ResultMapping.TEST_ID + " = " + TestMapping.ID + ")";

    @Override
    public boolean create(Test entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean update(Test entity) throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean delete(Integer entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public Test selectEntityById(Integer entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDao().
                    selectEntityById(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Test> selectByCreatorId(int creatorId, int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao()
                    .selectSortedTestsByIntRow(limit,offset,desc,TestMapping.CREATOR_ID,TestMapping.CREATOR_ID,creatorId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Test> selectByCategory(int categoryId, int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao()
                    .selectSortedTestsByIntRow(limit,offset,desc,TestMapping.CATEGORY_ID,TestMapping.CATEGORY_ID,categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Test> sortByUsersPassedNumber(int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    sortTestsByRow(limit,offset,desc, SORT_BY_USERS_NUMBER);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Test> sortByUsersPassedNumber(int categoryId,int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    selectSortedTestsByIntRow(limit,offset,desc, SORT_BY_USERS_NUMBER,TestMapping.CATEGORY_ID,categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Test> sortByName(int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    sortTestsByRow(limit,offset,desc,TestMapping.NAME);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Test> sortByName(int categoryId,int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    selectSortedTestsByIntRow(limit,offset,desc, TestMapping.NAME,TestMapping.CATEGORY_ID,categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Test> sortByCreationDate(int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    sortTestsByRow(limit,offset,desc,TestMapping.CREATION_DATE);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public List<Test> sortByCreationDate(int categoryId,int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    selectSortedTestsByIntRow(limit,offset,desc, TestMapping.CREATION_DATE,TestMapping.CATEGORY_ID,categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public boolean remove(Integer entityId) throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDao().removeTest(entityId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }

    @Override
    public int calculateTotalTestsNumber(Integer categoryId) throws ServiceException{
        try {
            if(categoryId==null) return DaoFactory.getInstance().getTestDao().calculateTotalTestsNumber();
            return DaoFactory.getInstance().getTestDao().calculateCategorizeTestsNumber(categoryId);
        } catch (DaoException e) {
            throw new ServiceException("msg",e);
        }
    }
}
