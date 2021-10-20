package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.parameterParser.ParserProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTest implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String NAME_TO_LONG = "test.tooLong";

    public static final int MAX_NAME_SIZE = 40;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

        Integer testId = ParserProvider.getInstance().getTestIdParser().parsing(request);
        if(testId == null){
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);
            return;
        }
        request.getSession().setAttribute(AttributeNames.TEST_ID , testId);

        Test test;
        try {
            test = factory.getTestService().selectEntityById(testId);
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        if(test==null){
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);
            return;
        }

        if(request.getMethod().equals(POST_METHOD)) {
            boolean needUpdate = false;
            String newName = request.getParameter(AttributeNames.TEST_NAME);
            if(newName != null){
                needUpdate = true;
                if(newName.length() > MAX_NAME_SIZE){

                    String language = ParserProvider.getInstance().getLanguageParser().parsing(request);

                    request.getSession().setAttribute(AttributeNames.CREATE_TEST_ERROR_MSG,
                            ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(NAME_TO_LONG));

                    TransitionManager.getInstance().getTransitionByRedirect().
                            doTransition(request, response,  PageMapping.EDIT_TESTS_PATH);

                    return;
                }
                test.setName(newName);
            }
            String newCategory = request.getParameter(AttributeNames.CATEGORY);
            if(newCategory != null && Integer.parseInt(newCategory) != test.getCategoryId()){
                needUpdate= true;
                test.setCategoryId(Integer.parseInt(newCategory));
            }
            if(needUpdate){
                try {
                    factory.getTestService().update(test);
                } catch (ServiceException e) {
                    LOGGER.error(e);
                    throw new ServletException(e);
                }
            }

            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
            return;
        }

        try {
            request.setAttribute(AttributeNames.QUESTION_LIST,
                    factory.getQuestionService().selectEntityByTestId(test.getId()));

            request.setAttribute(AttributeNames.CURRENT_TEST, test);

            request.setAttribute(AttributeNames.TEST_CATEGORY,
                    factory.getCategoryService().selectEntityById(test.getCategoryId()).getName());

            request.setAttribute(AttributeNames.CATEGORIES, factory.getCategoryService().selectAll());

        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }


        Object error = request.getSession().getAttribute(AttributeNames.CREATE_TEST_ERROR_MSG);
        if(error!=null){
            request.setAttribute(AttributeNames.ERROR_MSG,error.toString());
            request.getSession().removeAttribute(AttributeNames.CREATE_TEST_ERROR_MSG);
        }

        TransitionManager.getInstance().getTransitionByForward().
                doTransition(request, response, PageMapping.EDIT_TESTS);

    }
}
