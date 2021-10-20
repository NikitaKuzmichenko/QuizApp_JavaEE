package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.factory.EntitiesServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GetQuestion implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String ACTION_NEXT = "next";
    public static final String ACTION_PREVIOUS = "previous";

    public static final int QUESTION_PER_TIME = 1;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

        Object passingTestObject = session.getAttribute(AttributeNames.PASSING_TEST);
        if (passingTestObject == null) {
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
            return;
        }
        int testId = Integer.parseInt(passingTestObject.toString());

        int questionNumber;
        try {
            questionNumber = factory.getQuestionService().calculateQuestionNumber(testId);
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        Object questionPassedObject = session.getAttribute(AttributeNames.QUESTIONS_PASSED);
        if(questionPassedObject == null){
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
            return;
        }
        HashMap<Integer, Set<String>> questionPassed = (HashMap<Integer, Set<String>>)questionPassedObject;

        Object QuestionNumberObject = session.getAttribute(AttributeNames.CURRENT_QUESTION_NUMBER);
        int currentQuestionNumber;
        if(QuestionNumberObject == null){
            currentQuestionNumber = 0;
        }
        else{
            currentQuestionNumber = Integer.parseInt(QuestionNumberObject.toString());
            if(currentQuestionNumber < 0 ){
                currentQuestionNumber = 0;
            }
            else if(currentQuestionNumber >= questionNumber){
                currentQuestionNumber = questionNumber - 1;
            }
        }

        if(request.getMethod().equals(POST_METHOD)){

            String[] usersChoices = request.getParameterValues(AttributeNames.CORRECT_STATEMENT_LIST);
            if(usersChoices == null){
                questionPassed.put(currentQuestionNumber,null);
            }else{
                questionPassed.put(currentQuestionNumber,Set.of(usersChoices));
            }

            String action = request.getParameter(AttributeNames.ACTION);
            if(action!=null) {
                if (action.equals(ACTION_NEXT)) {
                    currentQuestionNumber++;
                }else if (action.equals(ACTION_PREVIOUS)) {
                    currentQuestionNumber--;
                }
            }

            session.setAttribute(AttributeNames.CURRENT_QUESTION_NUMBER,currentQuestionNumber);

            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response,PageMapping.GET_QUESTION_PATH);
            return;
        }

        List<Statement> statements;
        Question questions;
        try {
             questions = factory.getQuestionService().
                    selectEntityByTestId(testId, currentQuestionNumber, QUESTION_PER_TIME).get(0);

             statements = factory.getStatementService().selectByQuestionId(questions.getId());
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        Set<String> answers = questionPassed.get(currentQuestionNumber);
        if(answers!=null) {
            for (Statement statement : statements) {
                statement.setCorrect(answers.contains(Integer.toString(statement.getId())));
            }
        }
        else{
            for (Statement statement : statements) {
                statement.setCorrect(false);
            }
        }

        request.setAttribute(AttributeNames.STATEMENT_LIST,statements);
        request.setAttribute(AttributeNames.QUESTION_NAME,questions.getTitle());

        TransitionManager.getInstance().getTransitionByForward().
                doTransition(request, response, PageMapping.GET_QUESTION);
    }
}
