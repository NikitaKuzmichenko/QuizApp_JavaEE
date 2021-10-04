package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.parametersParsers.Parser;
import by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl.LanguageParser;
import by.epam.jwd.testingApp.controller.sideBar.SideBarCreator;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.validationService.componentValidator.AbstractStringValidator;
import by.epam.jwd.testingApp.service.validationService.componentValidator.EmailValidator;
import by.epam.jwd.testingApp.service.validationService.componentValidator.NameValidator;
import by.epam.jwd.testingApp.service.validationService.componentValidator.PasswordValidator;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgSupplier;
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

            SideBarCreator.newInstance().create(request);

            if(request.getMethod().equals("GET")){
                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response,"registrationPage");
                return;
            }

            Parser<String> languageParser = new LanguageParser();
            String language = languageParser.parsing(request);
            StringBuilder errorBuilder = new StringBuilder();

            User user = new User();
            user.setRoleId(1); // user role by default
            user.setEmail(request.getParameter(AttributeNames.EMAIL));
            user.setName(request.getParameter(AttributeNames.NICK_NAME));
            user.setPassword(request.getParameter(AttributeNames.PASSWORD));

            AbstractEntitiesValidator<User> entityValidator = new UserValidator();
            if(entityValidator.validateEntity(user, language,errorBuilder)){
                request.setAttribute("errorMsg", errorBuilder.toString());
                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response,"registrationPage");
                return;
            }

            if(!EntitiesServiceFactory.getInstance().getUserService().create(user)){
                request.setAttribute("errorMsg",
                        ErrorMsgProvider.newInstance().getManagerByLocale(language).getValueByName(USER_EXIST));
                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response,"registrationPage");
                return;
            }

            response.sendRedirect("controller/authorization");

        } catch (ServiceException e) {
            //
        }
    }
}
