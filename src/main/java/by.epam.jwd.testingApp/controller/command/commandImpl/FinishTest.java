package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.entity.Result;
import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractStatementService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class FinishTest implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

        Integer userId = ParserProvider.getInstance().getUserIdParser().parsing(request);
        if(userId == null){
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
            return;
        }

        Object passingTestObject = session.getAttribute(AttributeNames.PASSING_TEST);
        if (passingTestObject == null) {
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
            return;
        }
        int passingTestId = Integer.parseInt(passingTestObject.toString());

        Object questionPassedObject = session.getAttribute(AttributeNames.QUESTIONS_PASSED);
        if(questionPassedObject == null){
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
            return;
        }
        HashMap<Integer, Set<String>> questionPassed = (HashMap<Integer, Set<String>>)questionPassedObject;


        if(request.getMethod().equals(POST_METHOD)) {
            Object QuestionNumberObject = session.getAttribute(AttributeNames.CURRENT_QUESTION_NUMBER);
            int currentQuestionNumber;
            if(QuestionNumberObject== null){
                currentQuestionNumber =0;
            }else{
                currentQuestionNumber = Integer.parseInt(QuestionNumberObject.toString());
            }

            String[] usersChoices = request.getParameterValues(AttributeNames.CORRECT_STATEMENT_LIST);
            if(usersChoices == null){
                questionPassed.put(currentQuestionNumber,null);
            }else{
                questionPassed.put(currentQuestionNumber,Set.of(usersChoices));
            }

            try {
                List<Question> testsQuestions = EntitiesServiceFactory.getInstance().getQuestionService().
                        selectEntityByTestId(Integer.parseInt(passingTestObject.toString()));

                List<Statement> statements;
                AbstractStatementService statementService = factory.getStatementService();

                int correctAnswersNumber = 0;
                int totalQuestionsSize = testsQuestions.size();
                for(int i=0;i<totalQuestionsSize;i++){

                    statements = statementService.selectByQuestionId(testsQuestions.get(i).getId());
                    Set<String> answers = questionPassed.get(i);

                    if(answers!=null) {

                        for (Statement statement : statements) {
                            if (!statement.isCorrect() && answers.contains(Integer.toString(statement.getId()))) {
                                break;
                            }else{
                                correctAnswersNumber++;
                            }
                        }

                    }
                }
                double result = (correctAnswersNumber * 10000/ totalQuestionsSize);
                result = Math.ceil(result);

                Result userResult = new Result();
                userResult.setPassingDate(new Date(System.currentTimeMillis()));
                userResult.setResult((int)result);
                userResult.setTestId(passingTestId);
                userResult.setUserId(userId);

                if(!factory.getResultService().update(userResult)){
                    factory.getResultService().create(userResult);
                }

                TransitionManager.getInstance().getTransitionByRedirect().
                        doTransition(request, response, PageMapping.FINISH_TEST_PATH);
                return;

            } catch (ServiceException e) {
                throw new ServletException(e);
            }
        }

        List<Question> testsQuestions;
        int totalQuestionsSize;
        int correctAnswersNumber = 0;
        List<Boolean> isCorrect = new ArrayList<>();
        try {
             testsQuestions = EntitiesServiceFactory.getInstance().getQuestionService().
                    selectEntityByTestId(Integer.parseInt(passingTestObject.toString()));

            List<Statement> statements;
            AbstractStatementService statementService = factory.getStatementService();
            boolean isAnswerCorrect;


            totalQuestionsSize= testsQuestions.size();
            for(int i=0;i<totalQuestionsSize;i++){

                isAnswerCorrect = true;
                statements = statementService.selectByQuestionId(testsQuestions.get(i).getId());
                Set<String> answers = questionPassed.get(i);

                if(answers!=null) {
                    for (Statement statement : statements) {
                        if (!statement.isCorrect() && answers.contains(Integer.toString(statement.getId()))) {
                            isAnswerCorrect = false;
                            break;
                        }
                        else{
                            correctAnswersNumber++;
                        }
                    }

                    isCorrect.add(isAnswerCorrect);
                }
                else{
                    isCorrect.add(false);
                }

            }
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        double result = (correctAnswersNumber * 10000/ totalQuestionsSize);
        result = Math.ceil(result)/100;

        request.setAttribute(AttributeNames.QUESTION_LIST, testsQuestions);
        try {
            request.setAttribute(AttributeNames.TEST_NAME,
                    factory.getTestService().selectEntityById(passingTestId).getName());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        request.setAttribute(AttributeNames.CORRECT_ANSWER,isCorrect);
        request.setAttribute(AttributeNames.RESULT, result);

        TransitionManager.getInstance().getTransitionByForward().
                doTransition(request, response, PageMapping.FINISH_TEST);
    }
}
