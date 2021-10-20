package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.exception.ServiceException;
import by.epam.jwd.testingApp.service.entity.abstractService.AbstractQuestionService;
import by.epam.jwd.testingApp.service.entity.abstractService.AbstractStatementService;
import by.epam.jwd.testingApp.service.entity.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.parameterParser.ParserProvider;
import by.epam.jwd.testingApp.service.validation.entitiesValidator.EntitiesValidatorsProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EditQuestion implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String LONG_STATEMENT = "statement.long";

    public static final int MAX_STATEMENT_LENGTH = 50;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer questionId = ParserProvider.getInstance().getQuestionIdParser().parsing(request);
        if(questionId==null){
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
            return;
        }

        EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();
        HttpSession session = request.getSession();

        if(request.getMethod().equals(GET_METHOD)) {
            try {
                Question question = factory.getQuestionService().selectEntityById(questionId);
                if (question == null) {
                    TransitionManager.getInstance().getTransitionByRedirect().
                            doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
                }

                session.setAttribute(AttributeNames.QUESTION_ID, questionId);

                request.setAttribute(AttributeNames.QUESTION_NAME, question.getTitle());
                request.setAttribute(AttributeNames.STATEMENT_LIST,
                        factory.getStatementService().selectByQuestionId(questionId));

            } catch (ServiceException e) {
                LOGGER.error(e);
                throw new ServletException(e);
            }

            Object errorMsg = session.getAttribute(AttributeNames.CREATE_QUESTION_ERROR_MSG);
            if(errorMsg!=null){
                request.setAttribute(AttributeNames.ERROR_MSG,errorMsg.toString());
                session.removeAttribute(AttributeNames.CREATE_QUESTION_ERROR_MSG);
            }

            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.EDIT_QUESTION);

            return;
        }

        Integer testId = ParserProvider.getInstance().getTestIdParser().parsing(request);
        if (testId == null) {
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);
            return;
        }

        try {
            String language = ParserProvider.getInstance().getLanguageParser().parsing(request);
            StringBuilder errorBuilder = new StringBuilder();

            EntitiesValidatorsProvider entityValidatorProvider = EntitiesValidatorsProvider.getInstance();

            String title = request.getParameter(AttributeNames.QUESTION_NAME);
            if (title != null) {
                AbstractQuestionService questionService = factory.getQuestionService();
                Question question = questionService.selectEntityById(questionId);
                if (question == null) {
                    TransitionManager.getInstance().getTransitionByRedirect().
                            doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
                    return;
                }

                question.setTitle(title);
                if(!entityValidatorProvider.getQuestionValidator().validateEntity(question, language,errorBuilder)){
                    session.setAttribute(AttributeNames.CREATE_QUESTION_ERROR_MSG, errorBuilder.toString());

                    TransitionManager.getInstance().getTransitionByRedirect().
                            doTransition(request, response, PageMapping.EDIT_QUESTION_PATH);
                    return;
                }

                questionService.update(question);
            }


            String[] statementsCorrect = request.getParameterValues(AttributeNames.CORRECT_STATEMENT_LIST);
            List<String> isStatementsCorrect;
            if (statementsCorrect == null) {
                isStatementsCorrect = null;
            } else {
                isStatementsCorrect = Arrays.asList(statementsCorrect);
            }

            List<Statement> statements = factory.getStatementService().selectByQuestionId(questionId);
            if (statements != null) {
                AbstractStatementService statementService = factory.getStatementService();
                for (Statement statement : statements) {
                    if (isStatementsCorrect != null && isStatementsCorrect.contains(String.valueOf(statement.getId()))) {
                        if (!statement.isCorrect()) {
                            statement.setCorrect(true);
                            statementService.update(statement);
                        }
                    } else {
                        if (statement.isCorrect()) {
                            statement.setCorrect(false);
                            statementService.update(statement);
                        }
                    }
                }
            }

            boolean error = false;
            String[] statementsText = request.getParameterValues(AttributeNames.STATEMENT);
            if (statementsText != null && statementsText.length > 0) {
                AbstractStatementService statementService = factory.getStatementService();
                Statement statement = new Statement();
                statement.setQuestionId(questionId);
                for (int i = 0; i < statementsText.length; i++) {
                    if(statementsText[i].length() > MAX_STATEMENT_LENGTH){
                        error = true;
                        continue;
                    }
                    statement.setText(statementsText[i]);
                    statement.setCorrect(
                            isStatementsCorrect != null && isStatementsCorrect.contains(String.valueOf(i))
                    );
                    statementService.create(statement);
                }
            }

            if(error){
                session.setAttribute(AttributeNames.CREATE_QUESTION_ERROR_MSG,
                        ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(LONG_STATEMENT));

                TransitionManager.getInstance().getTransitionByRedirect().
                        doTransition(request, response, PageMapping.EDIT_QUESTION_PATH);
                return;
            }

        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request, response, PageMapping.EDIT_QUESTION_PATH);

    }
}
