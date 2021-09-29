package by.epam.jwd.testingApp.service.factory;

import by.epam.jwd.testingApp.service.abstractService.AbstractCategoryService;
import by.epam.jwd.testingApp.service.abstractService.AbstractCommentService;
import by.epam.jwd.testingApp.service.abstractService.AbstractQuestionService;
import by.epam.jwd.testingApp.service.abstractService.AbstractResultService;
import by.epam.jwd.testingApp.service.abstractService.AbstractRoleService;
import by.epam.jwd.testingApp.service.abstractService.AbstractStatementService;
import by.epam.jwd.testingApp.service.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.abstractService.AbstractUserService;
import by.epam.jwd.testingApp.service.serviceImpl.CategoryService;
import by.epam.jwd.testingApp.service.serviceImpl.CommentService;
import by.epam.jwd.testingApp.service.serviceImpl.QuestionService;
import by.epam.jwd.testingApp.service.serviceImpl.ResultService;
import by.epam.jwd.testingApp.service.serviceImpl.RoleService;
import by.epam.jwd.testingApp.service.serviceImpl.StatementService;
import by.epam.jwd.testingApp.service.serviceImpl.TestService;
import by.epam.jwd.testingApp.service.serviceImpl.UserService;

public class ServiceFactory {
    private static ServiceFactory instance = null;

    private final AbstractCategoryService categoryService = new CategoryService();
    private final AbstractCommentService commentService = new CommentService();
    private final AbstractQuestionService questionService = new QuestionService();
    private final AbstractResultService resultService = new ResultService();
    private final AbstractRoleService roleService = new RoleService();
    private final AbstractStatementService statementService = new StatementService();
    private final AbstractTestService testService = new TestService();
    private final AbstractUserService userService = new UserService();

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        ServiceFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ServiceFactory();
                }
            }
        }
        return localInstance;
    }

    public AbstractCategoryService getCategoryService() {
        return categoryService;
    }

    public AbstractCommentService getCommentService() {
        return commentService;
    }

    public AbstractQuestionService getQuestionService() {
        return questionService;
    }

    public AbstractResultService getResultService() {
        return resultService;
    }

    public AbstractRoleService getRoleService() {
        return roleService;
    }

    public AbstractStatementService getStatementService() {
        return statementService;
    }

    public AbstractTestService getTestService() {
        return testService;
    }

    public AbstractUserService getUserService() {
        return userService;
    }
}
