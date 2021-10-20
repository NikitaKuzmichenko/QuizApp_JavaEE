package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParser.ParserProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteQuestion implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Integer id = ParserProvider.getInstance().getQuestionIdParser().parsing(request);
            if(id!=null){
                EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();
                factory.getStatementService().deleteAllByQuestionId(id);
                factory.getQuestionService().delete(id);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
    }
}
