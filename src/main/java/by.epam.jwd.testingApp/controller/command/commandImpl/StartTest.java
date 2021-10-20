package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class StartTest implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer testId = ParserProvider.getInstance().getPassingTestParser().parsing(request);
        if(testId == null){
            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.WELCOME_PAGE);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute(AttributeNames.PASSING_TEST , testId);
        session.setAttribute(AttributeNames.CURRENT_QUESTION_NUMBER,0);

        Object resultMap = session.getAttribute(AttributeNames.QUESTIONS_PASSED);
        if(resultMap == null) {
            session.setAttribute(AttributeNames.QUESTIONS_PASSED, new HashMap<Integer, Set<String>>());
        }
        else{
            ((HashMap<Integer, Set<String>>)resultMap).clear();
        }

        try {
            request.setAttribute(AttributeNames.QUESTIONS_NUMBER_IN_TEST,
                    EntitiesServiceFactory.getInstance().getQuestionService().calculateQuestionNumber(testId));
            request.setAttribute(AttributeNames.TEST_NAME,
                    EntitiesServiceFactory.getInstance().getTestService().selectEntityById(testId).getName());

        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        TransitionManager.getInstance().getTransitionByForward().
                doTransition(request, response, PageMapping.START_TEST);
    }
}
