package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class FinishTest implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

        Object passingTestObject = session.getAttribute(AttributeNames.PASSING_TEST);
        if (passingTestObject == null) {
            TransitionManager.newInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
            return;
        }

        Object questionPassedObject = session.getAttribute(AttributeNames.QUESTIONS_PASSED);
        if(questionPassedObject == null){
            TransitionManager.newInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
            return;
        }
        HashMap<Integer,String[]> questionPassed = (HashMap<Integer,String[]>)questionPassedObject;

        TransitionManager.newInstance().getTransitionByForward().
                doTransition(request, response, PageMapping.FINISH_TEST);
    }
}
