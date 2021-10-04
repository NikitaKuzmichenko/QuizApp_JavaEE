package by.epam.jwd.testingApp.service.TestSortingService;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import java.util.List;

public interface TestsSorter {
    List<Test> doSorting(Integer sortParameter,int offset, boolean desc,int limit) throws ServiceException;
}
