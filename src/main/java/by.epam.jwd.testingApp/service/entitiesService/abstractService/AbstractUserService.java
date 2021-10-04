package by.epam.jwd.testingApp.service.entitiesService.abstractService;

import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService.GenericAbstractService;

import java.util.List;

public interface AbstractUserService extends GenericAbstractService<User,Integer> {
    User selectByLoginPassword(String email,String password) throws ServiceException;
    List<User> selectTestCreators(List<Test> tests) throws ServiceException;
}
