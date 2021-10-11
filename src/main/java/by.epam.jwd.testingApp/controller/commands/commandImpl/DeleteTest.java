package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTest implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();
            Integer testId  = ParserProvider.newInstance().getTestIdParser().parsing(request);

            if(testId!=null) {
                factory.getTestService().remove(testId);
            }

            TransitionManager.newInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);

        } catch (ServiceException e) {
            throw new ServletException(e);
        }

    }
}
