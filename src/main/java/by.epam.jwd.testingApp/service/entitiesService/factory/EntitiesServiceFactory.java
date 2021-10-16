package by.epam.jwd.testingApp.service.entitiesService.factory;

import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractCategoryService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractQuestionService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractResultService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractRoleService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractStatementService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractUserService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.CategoryService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.QuestionService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.ResultService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.RoleService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.StatementService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.TestService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.UserService;

public class EntitiesServiceFactory {

    private static AbstractCategoryService categoryService;
    private static AbstractQuestionService questionService;
    private static AbstractResultService resultService;
    private static AbstractRoleService roleService;
    private static AbstractStatementService statementService;
    private static AbstractTestService testService;
    private static AbstractUserService userService;

    private EntitiesServiceFactory(){
        categoryService = new CategoryService();
        questionService = new QuestionService();
        resultService = new ResultService();
        roleService = new RoleService();
        statementService = new StatementService();
        testService = new TestService();
        userService = new UserService();
    }

    private static class SingletonHolder {
        public static final EntitiesServiceFactory HOLDER_INSTANCE = new EntitiesServiceFactory();
    }

    public static EntitiesServiceFactory getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public AbstractCategoryService getCategoryService() {
        return categoryService;
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
