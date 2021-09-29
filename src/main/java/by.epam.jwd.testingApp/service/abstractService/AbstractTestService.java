package by.epam.jwd.testingApp.service.abstractService;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import by.epam.jwd.testingApp.service.abstractService.genericAbstractService.GenericAbstractService;

import java.util.List;

public interface AbstractTestService extends GenericAbstractService<Test,Integer> {
    List<Test> selectByCreatorId(int creatorId, int offset)throws ServiceException;
    List<Test> selectByCategory(int categoryId, int offset)throws ServiceException;
    List<Test> sortByUsersPassedNumber(int offset, boolean desc)throws ServiceException;
    List<Test> sortByName(int offset, boolean desc)throws ServiceException;
    List<Test> sortByCreationDate(int offset, boolean desc)throws ServiceException;
    boolean remove(Integer entityId) throws ServiceException;
}
