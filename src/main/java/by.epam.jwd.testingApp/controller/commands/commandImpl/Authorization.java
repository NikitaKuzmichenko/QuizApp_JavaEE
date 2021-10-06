package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.cookieService.CookieManager;
import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.LanguageParser;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgSupplier;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.passwordEncodingService.BCryptPasswordEncoder;
import by.epam.jwd.testingApp.service.passwordEncodingService.PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Authorization implements Command {

    public static final String INVALID_USER = "user.notExist";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {

            if(request.getMethod().equals("GET")){
                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response,PageMapping.AUTHORIZATION_PAGE);
                return;
            }
            User user =  EntitiesServiceFactory.getInstance().getUserService().selectByLogin(
                    request.getParameter(AttributeNames.EMAIL)
                    );

            boolean userValid = true;
            if(user!=null) {

                String password = request.getParameter(AttributeNames.PASSWORD);
                if(password !=null) {

                    PasswordEncoder encoder = new BCryptPasswordEncoder();
                    if (encoder.isMatching(password,user.getPassword())) {

                        HttpSession session = request.getSession();
                        CookieManager cookieManager = CookieManager.getInstance();
                        session.setAttribute(AttributeNames.USER_ID, user.getId());
                        if (request.getParameter(AttributeNames.REMEMBER_USER) != null) {
                            cookieManager.addCookie(response, new Cookie(AttributeNames.USER_ID, String.valueOf(user.getId())));
                        } else {
                            cookieManager.removeCookie(request, AttributeNames.USER_ID);
                            cookieManager.rewriteCookie(request, response);
                        }

                        TransitionManager.newInstance().getTransitionByRedirect().
                                doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
                    }else {
                        userValid = false;
                    }
                }else{
                    userValid = false;
                }
            }
            else{
                userValid = false;
            }

            if(!userValid){
                Parser<String> languageParser = new LanguageParser();
                String language = languageParser.parsing(request);
                ErrorMsgSupplier errorMsg = ErrorMsgProvider.newInstance().getManagerByLocale(language);
                request.setAttribute(AttributeNames.ERROR_MSG, errorMsg.getValueByName(INVALID_USER));

                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response, PageMapping.AUTHORIZATION_PAGE);
            }
        } catch (ServiceException e) {
            // redirect to error page
        }
    }
}
