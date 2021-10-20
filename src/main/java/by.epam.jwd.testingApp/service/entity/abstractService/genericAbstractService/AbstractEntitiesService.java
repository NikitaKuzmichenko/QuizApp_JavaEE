package by.epam.jwd.testingApp.service.entity.abstractService.genericAbstractService;

import by.epam.jwd.testingApp.exception.ServiceException;

public interface AbstractEntitiesService<E,K>{
    public  E selectEntityById(K id) throws ServiceException;
    public  boolean update(E entity) throws ServiceException;
    public  boolean delete(K id) throws ServiceException;
    public  boolean create(E entity) throws ServiceException;
}

