package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTest implements Command {
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
                needUpdate= true;
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
            throw new ServletException(e);
        }

        TransitionManager.getInstance().getTransitionByForward().
                doTransition(request, response, PageMapping.EDIT_TESTS);

    }
}
