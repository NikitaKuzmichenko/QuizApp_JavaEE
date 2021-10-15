package by.epam.jwd.testingApp.service.filter;

import by.epam.jwd.testingApp.controller.command.CommandAccessLevel;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgSupplier;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.LanguageParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessLvlCheckFilter implements Filter {

    public static final String USER_ACCESS_LACK = "user.accessLack";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String uri = request.getRequestURI();
        String command  = uri.substring(uri.lastIndexOf('/') + 1);

        boolean accessProvided;

        Object role = servletRequest.getAttribute(AttributeNames.USER_ROLE);
        if(role == null){
            accessProvided = CommandAccessLevel.getInstance().isEnoughRight(command,null);
        }else{
            accessProvided = CommandAccessLevel.getInstance().isEnoughRight(command,Integer.parseInt(role.toString()));
        }

        if(accessProvided) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Parser<String> languageParser = new LanguageParser();
        String language = languageParser.parsing(request);
        ErrorMsgSupplier errorMsg = ErrorMsgProvider.newInstance().getManagerByLocale(language);
        request.setAttribute(AttributeNames.ERROR_MSG, errorMsg.getValueByName(USER_ACCESS_LACK));

        TransitionManager.newInstance().getTransitionByForward().
                doTransition(request, response, PageMapping.AUTHORIZATION_PAGE);

    }

    @Override
    public void destroy() {
    }
}
