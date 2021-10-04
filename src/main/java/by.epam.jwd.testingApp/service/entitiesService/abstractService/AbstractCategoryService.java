package by.epam.jwd.testingApp.service.entitiesService.abstractService;

import by.epam.jwd.testingApp.entities.Category;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService.GenericAbstractService;

import java.util.List;

public interface AbstractCategoryService extends GenericAbstractService<Category,Integer> {
    List<Category> selectAll() throws ServiceException;
}
