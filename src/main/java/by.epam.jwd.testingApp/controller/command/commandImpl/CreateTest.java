package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;
import by.epam.jwd.testingApp.service.timeService.TimeProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTest implements Command {

    public static final String EMPTY_NAME = "test.emptyName";
    public static final String EMPTY_CATEGORY = "test.emptyCategory";
    public static final String UNDEFINED_USER = "test.undefinedUser";
    public static final String UNDEFINED_ERROR = "test.undefinedError";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

        try {
            request.setAttribute(AttributeNames.CATEGORIES, factory.getCategoryService().selectAll());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        if(request.getMethod().equals(GET_METHOD)) {
            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.CREATE_TESTS);
            return;
        }

        String language = ParserProvider.newInstance().getLanguageParser().parsing(request);

        String testName = request.getParameter(AttributeNames.TEST_NAME);
        if(testName == null){
            request.setAttribute(AttributeNames.ERROR_MSG,
                    ErrorMsgProvider.newInstance().getManagerByLocale(language).getValueByName(EMPTY_NAME));
            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response,  PageMapping.CREATE_TESTS);
            return;
        }

        String categoryId = request.getParameter(AttributeNames.CATEGORY);
        if(categoryId == null){
            request.setAttribute(AttributeNames.ERROR_MSG,
                    ErrorMsgProvider.newInstance().getManagerByLocale(language).getValueByName(EMPTY_CATEGORY));
            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response,  PageMapping.CREATE_TESTS);
            return;
        }

        Integer userId = ParserProvider.newInstance().getUserIdParser().parsing(request);
        if(userId == null){
            request.setAttribute(AttributeNames.ERROR_MSG,
                    ErrorMsgProvider.newInstance().getManagerByLocale(language).getValueByName(UNDEFINED_USER));
            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response,  PageMapping.CREATE_TESTS);
            return;
        }

        Test newTest = new Test();
        newTest.setName(testName);
        newTest.setCategoryId(Integer.parseInt(categoryId));
        newTest.setCreationDate(TimeProvider.newInstance().getSQLDate());
        newTest.setCreatorId(userId);

        Integer id;
        try {
            id = factory.getTestService().createAndGetId(newTest);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        if(id==null){
            request.setAttribute(AttributeNames.ERROR_MSG,
                    ErrorMsgProvider.newInstance().getManagerByLocale(language).getValueByName(UNDEFINED_ERROR));
            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response,  PageMapping.CREATE_TESTS);
            return;
        }

        request.getSession().setAttribute(AttributeNames.TEST_ID , id);

        TransitionManager.newInstance().getTransitionByRedirect().
                doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
    }
}
