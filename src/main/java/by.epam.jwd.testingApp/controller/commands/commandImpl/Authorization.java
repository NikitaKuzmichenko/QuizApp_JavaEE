package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.CookieManager;
import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.parametersParsers.Parser;
import by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl.LanguageParser;
import by.epam.jwd.testingApp.controller.sideBar.SideBarCreator;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgSupplier;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;

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
            SideBarCreator.newInstance().create(request);

            if(request.getMethod().equals("GET")){
                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response,"authorizationPage");
                return;
            }
            User user =  EntitiesServiceFactory.getInstance().getUserService().selectByLoginPassword(
                    request.getParameter(AttributeNames.EMAIL),
                    request.getParameter(AttributeNames.PASSWORD)
            );

            if(user!=null) {
                HttpSession session = request.getSession();
                CookieManager cookieManager = CookieManager.getInstance();
                session.setAttribute(AttributeNames.USER_ID, user.getId());
                if(request.getParameter(AttributeNames.REMEMBER_USER)!=null){
                    cookieManager.addCookie(response,new Cookie(AttributeNames.USER_ID,String.valueOf(user.getId())));
                }else{
                    cookieManager.removeCookie(request,AttributeNames.USER_ID);
                    cookieManager.rewriteCookie(request, response);
                }
                TransitionManager.newInstance().getTransitionByRedirect().
                        doTransition(request, response,"controller/page?num=1");
            }
            else{
                Parser<String> languageParser = new LanguageParser();
                String language = languageParser.parsing(request);
                ErrorMsgSupplier errorMsg = ErrorMsgProvider.newInstance().getManagerByLocale(language);
                request.setAttribute("errorMsg", errorMsg.getValueByName(INVALID_USER));

                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response,"authorizationPage");
            }

        } catch (ServiceException e) {
            //
        }
    }
}
