package by.epam.jwd.testingApp.service.entitiesService.abstractService;

import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractUserService extends AbstractEntitiesService<User,Integer> {
    User selectByEmail(String email) throws ServiceException;
    List<User> selectTestCreators(List<Test> tests) throws ServiceException;
}
