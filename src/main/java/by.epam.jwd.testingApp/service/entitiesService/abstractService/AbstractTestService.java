package by.epam.jwd.testingApp.service.entitiesService.abstractService;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService.GenericAbstractService;

import java.util.List;

public interface AbstractTestService extends GenericAbstractService<Test,Integer> {
    List<Test> selectByCreatorId(int creatorId, int offset, boolean desc,int limit)throws ServiceException;
    List<Test> selectByCategory(int categoryId, int offset, boolean desc,int limit)throws ServiceException;

    List<Test> sortByUsersPassedNumber(int offset, boolean desc,int limit)throws ServiceException;
    List<Test> sortByName(int offset, boolean desc,int limit)throws ServiceException;
    List<Test> sortByCreationDate(int offset, boolean desc,int limit)throws ServiceException;

    List<Test> sortByUsersPassedNumber(int categoryId,int offset, boolean desc,int limit)throws ServiceException;
    List<Test> sortByName(int categoryId,int offset, boolean desc,int limit)throws ServiceException;
    List<Test> sortByCreationDate(int categoryId,int offset, boolean desc,int limit)throws ServiceException;

    int calculateTotalTestsNumber(Integer categoryId) throws ServiceException;
    boolean remove(Integer entityId) throws ServiceException;
}
