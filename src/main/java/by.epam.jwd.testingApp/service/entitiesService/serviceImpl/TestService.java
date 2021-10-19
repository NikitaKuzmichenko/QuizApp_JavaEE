package by.epam.jwd.testingApp.service.entitiesService.serviceImpl;

import by.epam.jwd.testingApp.entity.Test;
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
        if(entity==null) return false;

        try {
            return DaoFactory.getInstance().getTestDao().
                    create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Test entity) throws ServiceException {
        if(entity==null) return false;

        try {
            return DaoFactory.getInstance().getTestDao().
                    update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer entityId) throws ServiceException {
        if(entityId==null) return false;
        try {
            return DaoFactory.getInstance().getTestDao().
                    delete(entityId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Test selectEntityById(Integer entityId) throws ServiceException {
        if(entityId==null) return null;
        try {
            return DaoFactory.getInstance().getTestDao().
                    selectEntityById(entityId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Test> selectByCreatorId(int creatorId, int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao()
                    .selectSortedTestsByIntRow(limit,offset,desc,TestMapping.CREATOR_ID,TestMapping.CREATOR_ID,creatorId,false);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Test> selectByCategory(int categoryId, int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao()
                    .selectSortedTestsByIntRow(limit,offset,desc,TestMapping.CATEGORY_ID,TestMapping.CATEGORY_ID,categoryId,true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Test> sortByUsersPassedNumber(int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    sortTestsByRow(limit,offset,desc, SORT_BY_USERS_NUMBER,true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Test> sortByUsersPassedNumber(int categoryId,int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    selectSortedTestsByIntRow(limit,offset,desc, SORT_BY_USERS_NUMBER,TestMapping.CATEGORY_ID,categoryId,true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Test> sortByName(int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    sortTestsByRow(limit,offset,desc,TestMapping.NAME,true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Test> sortByName(int categoryId,int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    selectSortedTestsByIntRow(limit,offset,desc, TestMapping.NAME,TestMapping.CATEGORY_ID,categoryId,true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Test> sortByCreationDate(int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    sortTestsByRow(limit,offset,desc,TestMapping.CREATION_DATE,true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Test> sortByCreationDate(int categoryId,int offset, boolean desc,int limit)throws ServiceException{
        try {
            return DaoFactory.getInstance().getTestDao().
                    selectSortedTestsByIntRow(limit,offset,desc, TestMapping.CREATION_DATE,TestMapping.CATEGORY_ID,categoryId,true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean remove(Integer entityId) throws ServiceException {
        if(entityId==null) return false;
        try {
            return DaoFactory.getInstance().getTestDao().removeTest(entityId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int calculateTotalTestsNumber(Integer categoryId) throws ServiceException{
        try {
            if(categoryId==null) {
                return DaoFactory.getInstance().getTestDao().
                        calculateTestsNumber(0, null, true,true);
            }
            return DaoFactory.getInstance().getTestDao().
                    calculateTestsNumber(categoryId,TestMapping.CATEGORY_ID,true,true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int calculateUsersTotalTestsNumber(Integer userId,boolean onlyAvailable) throws ServiceException{
        try {
            if(userId==null) {
                return DaoFactory.getInstance().getTestDao().
                        calculateTestsNumber(0, null,onlyAvailable,true);
            }
            return DaoFactory.getInstance().getTestDao().
                    calculateTestsNumber(userId,TestMapping.CREATOR_ID,onlyAvailable,true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer createAndGetId(Test entity)throws ServiceException{
        if(entity==null)return null;
        try {
            return DaoFactory.getInstance().getTestDao().
                    createAndGetId(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
