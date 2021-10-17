package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTest implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Integer testId  = ParserProvider.getInstance().getTestIdParser().parsing(request);
            if(testId!=null) {
                EntitiesServiceFactory.getInstance().getTestService().remove(testId);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);
    }
}
