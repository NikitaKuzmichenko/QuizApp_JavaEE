package by.epam.jwd.testingApp.service.testSorting;

import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.factory.EntitiesServiceFactory;

import java.util.List;

public class TestsSorterByUsersPassed implements TestsSorter{
    @Override
    public List<Test> doSorting(Integer sortParameter, int offset, boolean desc, int limit) throws ServiceException {
        if(sortParameter == null){
            return EntitiesServiceFactory.getInstance().getTestService().
                    sortByUsersPassedNumber(offset, desc,limit);
        }
        return EntitiesServiceFactory.getInstance().getTestService().
                sortByUsersPassedNumber(sortParameter, offset, desc,limit);
    }
}
