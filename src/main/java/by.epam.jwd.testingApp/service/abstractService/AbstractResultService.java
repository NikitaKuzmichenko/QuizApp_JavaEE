package by.epam.jwd.testingApp.service.abstractService;

import by.epam.jwd.testingApp.entities.Pair;
import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.abstractService.genericAbstractService.GenericAbstractService;

import java.util.List;

public interface AbstractResultService extends GenericAbstractService<Result, Pair<Integer,Integer>> {
    Integer calculateRowsNumberByTestId(int testId) throws ServiceException;
    Integer calculateAvgResultByTestId(int testId) throws ServiceException;
    Integer calculateAvgResultByUserId(int userId)throws ServiceException;
    List<Result> selectByTestId(int testId) throws ServiceException;
    List<Result> selectByUserId(int userId, int offset) throws ServiceException;

}
