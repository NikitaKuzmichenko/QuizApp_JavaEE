package by.epam.jwd.testingApp.service.entity.abstractService;

import by.epam.jwd.testingApp.entity.Role;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractRoleService extends AbstractEntitiesService<Role,Integer> {
    List<Role> selectAll() throws ServiceException;
}
