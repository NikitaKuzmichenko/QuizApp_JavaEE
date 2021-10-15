package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.passwordEncodingService.PasswordEncoder;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.passwordEncodingService.PasswordEncoderProvider;
import by.epam.jwd.testingApp.service.validationService.entitiesValidator.EntitiesValidatorsProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Registration implements Command {

    public static final String USER_EXIST = "user.alreadyExist";
    public static final int DEFAULT_ROLE = 1;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getMethod().equals(GET_METHOD)){
            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response,PageMapping.REGISTRATION_PAGE);
            return;
        }

        String language = ParserProvider.newInstance().getLanguageParser().parsing(request);
        StringBuilder errorBuilder = new StringBuilder();

        User user = new User();
        user.setRoleId(DEFAULT_ROLE);
        user.setEmail(request.getParameter(AttributeNames.EMAIL));
        user.setName(request.getParameter(AttributeNames.NICK_NAME));
        user.setPassword(request.getParameter(AttributeNames.PASSWORD));

        EntitiesValidatorsProvider entityValidatorProvider = EntitiesValidatorsProvider.newInstance();
        if(!entityValidatorProvider.getUserValidator().validateEntity(user, language,errorBuilder)){
            request.setAttribute(AttributeNames.ERROR_MSG, errorBuilder.toString());
            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response,PageMapping.REGISTRATION_PAGE);
            return;
        }

        PasswordEncoder encoder = PasswordEncoderProvider.newInstance().getBCryptPasswordEncoder();
        user.setPassword(encoder.encrypt(user.getPassword()));

        try {
            if(!EntitiesServiceFactory.getInstance().getUserService().create(user)){
                request.setAttribute(AttributeNames.ERROR_MSG,
                        ErrorMsgProvider.newInstance().getManagerByLocale(language).getValueByName(USER_EXIST));
                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response, PageMapping.REGISTRATION_PAGE);
                return;
            }
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        TransitionManager.newInstance().getTransitionByRedirect().
                doTransition(request, response,PageMapping.TO_AUTHORIZATION_PAGE_PATH);

    }
}
