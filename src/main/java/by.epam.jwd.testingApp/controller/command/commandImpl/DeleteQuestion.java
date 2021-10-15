package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteQuestion implements Command {
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
            throw new ServletException(e);
        }

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
    }
}
