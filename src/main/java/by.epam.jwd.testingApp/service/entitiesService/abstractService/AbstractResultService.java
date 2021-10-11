package by.epam.jwd.testingApp.service.entitiesService.abstractService;

import by.epam.jwd.testingApp.entities.Pair;
import by.epam.jwd.testingApp.entities.Result;
import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractResultService extends AbstractEntitiesService<Result, Pair<Integer,Integer>> {
    Integer calculateRowsNumberByTestId(int testId) throws ServiceException;
    List<Integer> calculateRowsNumberByTestId(List<Test> testId) throws ServiceException;
    Integer calculateAvgResultByTestId(int testId) throws ServiceException;
    Integer calculateAvgResultByUserId(int userId)throws ServiceException;
    List<Result> selectByTestId(int testId) throws ServiceException;
    List<Result> selectByUserId(int userId, int offset,int limit) throws ServiceException;

    List<Double> calculateAvgResultsByTestId(List<Test> tests) throws ServiceException;

}
