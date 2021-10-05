package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.LanguageParser;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.passwordEncodingService.BCryptPasswordEncoder;
import by.epam.jwd.testingApp.service.passwordEncodingService.PasswordEncoder;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.validationService.entitiesValidator.AbstractEntitiesValidator;
import by.epam.jwd.testingApp.service.validationService.entitiesValidator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Registration implements Command {

    public static final String USER_EXIST = "user.alreadyExist";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            if(request.getMethod().equals("GET")){
                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response,PageMapping.REGISTRATION_PAGE);
                return;
            }

            Parser<String> languageParser = new LanguageParser();
            String language = languageParser.parsing(request);
            StringBuilder errorBuilder = new StringBuilder();

            User user = new User();
            user.setRoleId(1); // user roleId by default
            user.setEmail(request.getParameter(AttributeNames.EMAIL));
            user.setName(request.getParameter(AttributeNames.NICK_NAME));
            user.setPassword(request.getParameter(AttributeNames.PASSWORD));

            AbstractEntitiesValidator<User> entityValidator = new UserValidator();
            if(!entityValidator.validateEntity(user, language,errorBuilder)){
                request.setAttribute(AttributeNames.ERROR_MSG, errorBuilder.toString());
                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response,PageMapping.REGISTRATION_PAGE);
                return;
            }

            PasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encrypt(user.getPassword()));

            if(!EntitiesServiceFactory.getInstance().getUserService().create(user)){
                request.setAttribute(AttributeNames.ERROR_MSG,
                        ErrorMsgProvider.newInstance().getManagerByLocale(language).getValueByName(USER_EXIST));
                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response, PageMapping.REGISTRATION_PAGE);
                return;
            }

            response.sendRedirect(PageMapping.TO_AUTHORIZATION_PAGE_PATH);

        } catch (ServiceException e) {
            // redirect to error page
        }
    }
}
