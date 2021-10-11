package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.Question;
import by.epam.jwd.testingApp.entities.Statement;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractStatementService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddQuestion implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            if(request.getMethod().equals("POST")){

                Integer testId = ParserProvider.newInstance().getTestIdParser().parsing(request);
                if(testId==null){
                    TransitionManager.newInstance().getTransitionByRedirect().
                            doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);
                    return;
                }

                EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();
                String title = request.getParameter(AttributeNames.QUESTION_NAME);
                if(title==null){
                    TransitionManager.newInstance().getTransitionByRedirect().
                            doTransition(request, response, PageMapping.ADD_QUESTION_PATH);
                    return;
                }

                Question question = new Question();
                question.setTitle(title);
                question.setTestId(testId);

                Integer questionId = factory.getQuestionService().createAndGetId(question);
                String[] statementsText = request.getParameterValues(AttributeNames.STATEMENT);
                List<String> isStatementsCorrect = Arrays.asList(request.getParameterValues(AttributeNames.CORRECT_STATEMENT_LIST));
                if(statementsText!=null) {
                    AbstractStatementService statementService = factory.getStatementService();
                    Statement statement = new Statement();
                    statement.setQuestionId(questionId);
                    for(int i=0;i<statementsText.length;i++){
                        statement.setText(statementsText[i]);
                        statement.setCorrect(isStatementsCorrect.contains(String.valueOf(i)));
                        statementService.create(statement);
                    }
                }

                TransitionManager.newInstance().getTransitionByRedirect().
                        doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
                return;
            }

            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.ADD_QUESTION);

        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
