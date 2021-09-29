package by.epam.jwd.testingApp.service.abstractService;

import by.epam.jwd.testingApp.entities.category.Category;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.abstractService.genericAbstractService.GenericAbstractService;

import java.util.List;

public interface AbstractCategoryService extends GenericAbstractService<Category,Integer> {
    List<Category> selectAll() throws ServiceException;
}
