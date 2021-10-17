package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.cookieService.CookieManager;
import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.passwordEncodingService.PasswordEncode;

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

        HttpSession session = request.getSession();

        if(request.getMethod().equals(GET_METHOD)){

            Object error = session.getAttribute(AttributeNames.AUTHORIZATION_ERROR_MSG);
            if(error!=null){
                request.setAttribute(AttributeNames.ERROR_MSG,error.toString());
                session.removeAttribute(AttributeNames.AUTHORIZATION_ERROR_MSG);
            }

            TransitionManager.getInstance().getTransitionByForward().
                    doTransition(request, response,PageMapping.AUTHORIZATION_PAGE);
            return;
        }

        User user;
        try {
            user = EntitiesServiceFactory.getInstance().getUserService().selectByLogin(
                    request.getParameter(AttributeNames.EMAIL).trim());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        if(user!=null) {
            String password = request.getParameter(AttributeNames.PASSWORD);
            if(password !=null) {
                if (PasswordEncode.getInstance().isMatching(password,user.getPassword())) {
                    CookieManager cookieManager = CookieManager.getInstance();
                    session.setAttribute(AttributeNames.USER_ID, user.getId());
                    if (request.getParameter(AttributeNames.REMEMBER_USER) != null) {
                        cookieManager.addCookie(response, new Cookie(AttributeNames.USER_ID, String.valueOf(user.getId())));
                    } else {
                        cookieManager.removeCookie(request, AttributeNames.USER_ID);
                        cookieManager.rewriteCookie(request, response);
                    }

                    TransitionManager.getInstance().getTransitionByRedirect().
                            doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
                    return;
                }
            }
        }

        String language = ParserProvider.getInstance().getLanguageParser().parsing(request);

        session.setAttribute(AttributeNames.AUTHORIZATION_ERROR_MSG,
                ErrorMsgProvider.getInstance().getManagerByLocale(language).getValueByName(INVALID_USER));

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request, response, PageMapping.TO_AUTHORIZATION_PAGE_PATH);

    }
}
