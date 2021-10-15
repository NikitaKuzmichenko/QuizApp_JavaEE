package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeStatus implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer testId = ParserProvider.getInstance().getTestIdParser().parsing(request);
        if(testId!=null){
            try {
                AbstractTestService testService = EntitiesServiceFactory.getInstance().getTestService();
                Test test = testService.selectEntityById(testId);
                test.setAvailable(!test.isAvailable());
                testService.update(test);
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
        }

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request,response, PageMapping.VIEW_MY_TESTS_PATH);
    }
}
