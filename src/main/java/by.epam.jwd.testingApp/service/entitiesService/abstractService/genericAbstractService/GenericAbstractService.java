package by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService;

import by.epam.jwd.testingApp.exceptions.ServiceException;

public interface GenericAbstractService<E,K>{
    public  E selectEntityById(K id) throws ServiceException;
    public  boolean update(E entity) throws ServiceException;
    public  boolean delete(K id) throws ServiceException;
    public  boolean create(E entity) throws ServiceException;
}

