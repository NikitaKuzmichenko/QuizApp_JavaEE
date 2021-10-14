package by.epam.jwd.testingApp.service.entitiesService.abstractService;

import by.epam.jwd.testingApp.entity.Comment;

import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.genericAbstractService.AbstractEntitiesService;

import java.util.List;

public interface AbstractCommentService extends AbstractEntitiesService<Comment,Integer> {
     List<Comment> selectEntityByTestId(Integer testId)throws ServiceException;
}
