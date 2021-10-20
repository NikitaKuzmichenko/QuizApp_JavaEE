package by.epam.jwd.testingApp.service.entity.abstractService;

import by.epam.jwd.testingApp.entity.Category;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractCategoryService extends AbstractEntitiesService<Category,Integer> {
    List<Category> selectAll() throws ServiceException;
}
