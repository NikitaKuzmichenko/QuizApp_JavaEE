package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class StartTest implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer testId = ParserProvider.newInstance().getPassingTestParser().parsing(request);
            if(testId == null){
                TransitionManager.newInstance().getTransitionByForward().
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

            request.setAttribute(AttributeNames.QUESTIONS_NUMBER_IN_TEST,
                    EntitiesServiceFactory.getInstance().getQuestionService().calculateQuestionNumber(testId));
            request.setAttribute(AttributeNames.TEST_NAME,
                    EntitiesServiceFactory.getInstance().getTestService().selectEntityById(testId).getName());

            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.START_TEST);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
