package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.entity.Statement;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractStatementService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.parameterParser.ParserProvider;
import by.epam.jwd.testingApp.service.validationService.entitiesValidator.EntitiesValidatorsProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddQuestion implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String LONG_STATEMENT = "statement.long";
    public static final int MAX_STATEMENT_LENGTH = 50;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if(request.getMethod().equals(GET_METHOD)) {

            Object error = session.getAttribute(AttributeNames.CREATE_QUESTION_ERROR_MSG);
            if(error!=null){
                request.setAttribute(AttributeNames.ERROR_MSG,error.toString());
                session.removeAttribute(AttributeNames.CREATE_QUESTION_ERROR_MSG);
            }

            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.ADD_QUESTION);
        }

        Integer testId = ParserProvider.getInstance().getTestIdParser().parsing(request);
        if(testId==null){
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.VIEW_MY_TESTS_PATH);
            return;
        }

        EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();
        String title = request.getParameter(AttributeNames.QUESTION_NAME);
        if(title==null){
            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.ADD_QUESTION_PATH);
            return;
        }

        Question question = new Question();
        question.setTitle(title.trim());
        question.setTestId(testId);

        String language = ParserProvider.getInstance().getLanguageParser().parsing(request);
        StringBuilder errorBuilder = new StringBuilder();

        EntitiesValidatorsProvider entityValidatorProvider = EntitiesValidatorsProvider.getInstance();
        if(!entityValidatorProvider.getQuestionValidator().validateEntity(question, language,errorBuilder)){
            session.setAttribute(AttributeNames.CREATE_QUESTION_ERROR_MSG, errorBuilder.toString());

            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.ADD_QUESTION_PATH);
            return;
        }

        boolean error = false;
        try {
            AbstractStatementService statementService = factory.getStatementService();

            Integer questionId = factory.getQuestionService().createAndGetId(question);
            String[] statementsText = request.getParameterValues(AttributeNames.STATEMENT);

            if(statementsText != null && statementsText.length > 0) {

                String[] correctStatementsID =request.getParameterValues(AttributeNames.CORRECT_STATEMENT_LIST);
                List<String> isStatementsCorrect = null;
                if(correctStatementsID != null){
                    isStatementsCorrect = Arrays.asList(correctStatementsID);
                }

                if (statementsText != null) {
                    Statement statement = new Statement();
                    statement.setQuestionId(questionId);
                    for (int i = 0; i < statementsText.length; i++) {
                        if(statementsText[i].length() > MAX_STATEMENT_LENGTH) {
                            error = true;
                            continue;
                        }
                        statement.setText(statementsText[i]);
                        statement.setCorrect(
                                isStatementsCorrect!=null && isStatementsCorrect.contains(String.valueOf(i))
                        );
                        statementService.create(statement);
                    }
                }

            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        if(error){
            session.setAttribute(AttributeNames.CREATE_QUESTION_ERROR_MSG,
                    ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(LONG_STATEMENT));

            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.EDIT_QUESTION_PATH);
            return;
        }

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request, response, PageMapping.EDIT_TESTS_PATH);
    }
}
