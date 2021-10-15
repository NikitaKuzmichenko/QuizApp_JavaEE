package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.service.cookieService.CookieManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut implements Command {

    public final static String NO_USER = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeCookie(request,AttributeNames.USER_ID);
        cookieManager.rewriteCookie(request,response);

        HttpSession session = request.getSession();
        session.setAttribute(AttributeNames.USER_ID, NO_USER);

        TransitionManager.getInstance().getTransitionByRedirect().
                doTransition(request, response, PageMapping.TO_WELCOME_PAGE_PATH);
    }
}
