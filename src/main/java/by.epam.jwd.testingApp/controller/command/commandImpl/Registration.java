package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.passwordEncodingService.PasswordEncode;
import by.epam.jwd.testingApp.service.validationService.entitiesValidator.EntitiesValidatorsProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Registration implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String USER_EXIST = "user.alreadyExist";
    public static final int DEFAULT_ROLE = 1;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if(request.getMethod().equals(GET_METHOD)){

            Object error = session.getAttribute(AttributeNames.REGISTRATION_ERROR_MSG);
            if(error!=null){
                request.setAttribute(AttributeNames.ERROR_MSG,error.toString());
                session.removeAttribute(AttributeNames.REGISTRATION_ERROR_MSG);
            }
            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response,PageMapping.REGISTRATION_PAGE);
            return;
        }


        User user = new User();
        user.setRoleId(DEFAULT_ROLE);
        user.setEmail(request.getParameter(AttributeNames.EMAIL).trim());
        user.setName(request.getParameter(AttributeNames.NICK_NAME).trim());
        user.setPassword(request.getParameter(AttributeNames.PASSWORD));

        String language = ParserProvider.getInstance().getLanguageParser().parsing(request);
        StringBuilder errorBuilder = new StringBuilder();

        EntitiesValidatorsProvider entityValidatorProvider = EntitiesValidatorsProvider.getInstance();
        if(!entityValidatorProvider.getUserValidator().validateEntity(user, language,errorBuilder)){
            session.setAttribute(AttributeNames.REGISTRATION_ERROR_MSG, errorBuilder.toString());

            TransitionManager.getInstance().getTransitionByRedirect().
                    doTransition(request, response, PageMapping.REGISTRATION_PAGE_PATH);
            return;
        }

        user.setPassword(PasswordEncode.getInstance().encrypt(user.getPassword()));

        try {
            if(!EntitiesServiceFactory.getInstance().getUserService().create(user)){
                session.setAttribute(AttributeNames.REGISTRATION_ERROR_MSG,
                        ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(USER_EXIST));

                TransitionManager.getInstance().getTransitionByRedirect().
                        doTransition(request, response, PageMapping.REGISTRATION_PAGE_PATH);
                return;
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request, response,PageMapping.TO_AUTHORIZATION_PAGE_PATH);

    }
}
