package by.epam.jwd.testingApp.service.abstractService;

import by.epam.jwd.testingApp.entities.role.Role;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.abstractService.genericAbstractService.GenericAbstractService;

import java.util.List;

public interface AbstractRoleService extends GenericAbstractService<Role,Integer> {
    List<Role> selectAll() throws ServiceException;
}
