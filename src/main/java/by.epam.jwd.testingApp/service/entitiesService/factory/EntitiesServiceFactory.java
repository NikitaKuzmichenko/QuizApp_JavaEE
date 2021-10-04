package by.epam.jwd.testingApp.service.entitiesService.factory;

import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractCategoryService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractCommentService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractQuestionService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractResultService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractRoleService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractStatementService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractUserService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.CategoryService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.CommentService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.QuestionService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.ResultService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.RoleService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.StatementService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.TestService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.UserService;

public class EntitiesServiceFactory {
    private static EntitiesServiceFactory instance = null;

    private AbstractCategoryService categoryService;
    private AbstractCommentService commentService;
    private AbstractQuestionService questionService;
    private AbstractResultService resultService;
    private AbstractRoleService roleService;
    private AbstractStatementService statementService;
    private AbstractTestService testService;
    private AbstractUserService userService;

    private EntitiesServiceFactory(){
        categoryService = new CategoryService();
        commentService = new CommentService();
        questionService = new QuestionService();
        resultService = new ResultService();
        roleService = new RoleService();
        statementService = new StatementService();
        testService = new TestService();
        userService = new UserService();
    }

    public static EntitiesServiceFactory getInstance() {
        EntitiesServiceFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (EntitiesServiceFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EntitiesServiceFactory();
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
