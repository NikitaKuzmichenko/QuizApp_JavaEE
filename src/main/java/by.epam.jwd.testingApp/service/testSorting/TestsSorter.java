package by.epam.jwd.testingApp.service.testSorting;

import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exception.ServiceException;

import java.util.List;

public interface TestsSorter {
    List<Test> doSorting(Integer sortParameter,int offset, boolean desc,int limit) throws ServiceException;
}
