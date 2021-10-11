package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.Test;
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

        try {

            EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

            Integer testId = ParserProvider.newInstance().getTestIdParser().parsing(request);
            if(testId == null){
                TransitionManager.newInstance().getTransitionByRedirect().
                        doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);
                return;
            }
            request.getSession().setAttribute(AttributeNames.TEST_ID , testId);

            Test test = factory.getTestService().selectEntityById(testId);
            if(test==null){
                TransitionManager.newInstance().getTransitionByRedirect().
                        doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);
                return;
            }

            if(request.getMethod().equals("POST")) {
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
                    factory.getTestService().update(test);
                }

                TransitionManager.newInstance().getTransitionByRedirect().
                        doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
                return;
            }


            request.setAttribute(AttributeNames.QUESTION_LIST,
                    factory.getQuestionService().selectEntityByTestId(test.getId()));

            request.setAttribute(AttributeNames.CURRENT_TEST, test);

            request.setAttribute(AttributeNames.TEST_CATEGORY,
                    factory.getCategoryService().selectEntityById(test.getCategoryId()).getName());

            request.setAttribute(AttributeNames.CATEGORIES, factory.getCategoryService().selectAll());

            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.EDIT_TESTS);

        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
