package by.epam.jwd.testingApp.service.abstractService;

import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.abstractService.genericAbstractService.GenericAbstractService;

public interface AbstractUserService extends GenericAbstractService<User,Integer> {
    User selectByLoginPassword(String email,String password) throws ServiceException;
}
