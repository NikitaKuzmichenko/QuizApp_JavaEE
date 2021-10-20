package by.epam.jwd.testingApp.service.entity.abstractService;

import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractUserService extends AbstractEntitiesService<User,Integer> {
    User selectByEmail(String email) throws ServiceException;
    List<User> selectTestCreators(List<Test> tests) throws ServiceException;
}
