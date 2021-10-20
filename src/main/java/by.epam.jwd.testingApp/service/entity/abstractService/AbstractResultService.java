package by.epam.jwd.testingApp.service.entity.abstractService;

import by.epam.jwd.testingApp.entity.Pair;
import by.epam.jwd.testingApp.entity.Result;
import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractResultService extends AbstractEntitiesService<Result, Pair<Integer,Integer>> {
    Integer calculateRowsNumberByTestId(int testId) throws ServiceException;
    List<Integer> calculateRowsNumberByTestId(List<Test> testId) throws ServiceException;
    Integer calculateRowsNumberByUserId(int userId) throws ServiceException;
    Integer calculateAvgResultByTestId(int testId) throws ServiceException;
    List<Result> selectByTestId(int testId) throws ServiceException;
    List<Result> selectByUserId(int userId, int offset,int limit) throws ServiceException;

    List<Double> calculateAvgResultsByTestId(List<Test> tests) throws ServiceException;

}
