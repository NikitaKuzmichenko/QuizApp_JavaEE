package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.Question;
import by.epam.jwd.testingApp.entities.Statement;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractQuestionService;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractStatementService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditQuestion implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Integer questionId = ParserProvider.newInstance().getQuestionIdParser().parsing(request);
            if(questionId==null){
                TransitionManager.newInstance().getTransitionByRedirect().
                        doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
                return;
            }

            EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

            if(request.getMethod().equals("POST")){

                Integer testId = ParserProvider.newInstance().getTestIdParser().parsing(request);
                if(testId==null){
                    TransitionManager.newInstance().getTransitionByRedirect().
                            doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);
                    return;
                }

                String title = request.getParameter(AttributeNames.QUESTION_NAME);
                if(title!=null) {
                    AbstractQuestionService  questionService =  factory.getQuestionService();
                    Question question = questionService.selectEntityById(questionId);
                    if(question==null){
                        TransitionManager.newInstance().getTransitionByRedirect().
                                doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
                        return;
                    }
                    question.setTitle(title);
                    questionService.update(question);
                }

                String[] statementsText = request.getParameterValues(AttributeNames.STATEMENT);
                String[] statementsCorrect = request.getParameterValues(AttributeNames.CORRECT_STATEMENT_LIST);
                List<String> isStatementsCorrect;
                if(statementsCorrect==null){
                    isStatementsCorrect = new ArrayList<>();
                }else {
                    isStatementsCorrect = Arrays.asList(statementsCorrect);
                }

                List<Statement> statements = factory.getStatementService().selectByQuestionId(questionId);
                if(statements!=null){
                    AbstractStatementService statementService = factory.getStatementService();
                    for(Statement statement:statements){
                        if(isStatementsCorrect.contains(String.valueOf(statement.getId()))){
                            if(!statement.isCorrect()){
                                statement.setCorrect(true);
                                statementService.update(statement);
                            }
                        }
                        else{
                            if(statement.isCorrect()){
                                statement.setCorrect(false);
                                statementService.update(statement);
                            }
                        }
                    }
                }

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

            Question question = factory.getQuestionService().selectEntityById(questionId);
            if(question == null){
                TransitionManager.newInstance().getTransitionByRedirect().
                        doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
            }

            request.getSession().setAttribute(AttributeNames.QUESTION_ID,questionId);

            request.setAttribute(AttributeNames.QUESTION_NAME, question.getTitle());
            request.setAttribute(AttributeNames.STATEMENT_LIST,factory.getStatementService().selectByQuestionId(questionId));

            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.EDIT_QUESTION);

        } catch (ServiceException e) {
            throw new ServletException(e);
        }

    }
}
