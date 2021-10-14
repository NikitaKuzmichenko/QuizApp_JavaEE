package by.epam.jwd.testingApp.service.testSortingService;

import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import java.util.List;

public interface TestsSorter {
    List<Test> doSorting(Integer sortParameter,int offset, boolean desc,int limit) throws ServiceException;
}
