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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CreateTest implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String EMPTY_NAME = "test.emptyName";
    public static final String NAME_TO_LONG = "test.tooLong";
    public static final String EMPTY_CATEGORY = "test.emptyCategory";
    public static final String UNDEFINED_USER = "test.undefinedUser";
    public static final String UNDEFINED_ERROR = "test.undefinedError";

    public static final int MAX_NAME_SIZE = 40;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

        try {
            request.setAttribute(AttributeNames.CATEGORIES, factory.getCategoryService().selectAll());
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        if(request.getMethod().equals(GET_METHOD)) {
            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.CREATE_TESTS);
            return;
        }

        String language = ParserProvider.getInstance().getLanguageParser().parsing(request);

        String testName = request.getParameter(AttributeNames.TEST_NAME);
        if(testName == null){
            request.setAttribute(AttributeNames.ERROR_MSG,
                    ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(EMPTY_NAME));
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response,  PageMapping.CREATE_TESTS_PATH);
            return;
        }

        if(testName.length() > MAX_NAME_SIZE){
            request.setAttribute(AttributeNames.ERROR_MSG,
                    ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(NAME_TO_LONG));
            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response,  PageMapping.CREATE_TESTS);
            return;
        }

        String categoryId = request.getParameter(AttributeNames.CATEGORY);
        if(categoryId == null){
            request.setAttribute(AttributeNames.ERROR_MSG,
                    ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(EMPTY_CATEGORY));
            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response,  PageMapping.CREATE_TESTS);
            return;
        }

        Integer userId = ParserProvider.getInstance().getUserIdParser().parsing(request);
        if(userId == null){
            request.setAttribute(AttributeNames.ERROR_MSG,
                    ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(UNDEFINED_USER));
            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response,  PageMapping.CREATE_TESTS);
            return;
        }

        Test newTest = new Test();
        newTest.setName(testName);
        newTest.setCategoryId(Integer.parseInt(categoryId));
        newTest.setCreationDate(new Date(System.currentTimeMillis()));
        newTest.setCreatorId(userId);

        Integer id;
        try {
            id = factory.getTestService().createAndGetId(newTest);
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        if(id==null){
            request.setAttribute(AttributeNames.ERROR_MSG,
                    ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(UNDEFINED_ERROR));
            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response,  PageMapping.CREATE_TESTS);
            return;
        }

        request.getSession().setAttribute(AttributeNames.TEST_ID , id);

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
    }
}
