package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStatement implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter(AttributeNames.STATEMENT_ID);
            if(id!=null){
                EntitiesServiceFactory.getInstance().getStatementService().delete(Integer.parseInt(id));
            }

            TransitionManager.newInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.EDIT_QUESTION_PATH);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
