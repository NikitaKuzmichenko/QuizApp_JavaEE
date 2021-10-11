package by.epam.jwd.testingApp.service.filters;

import by.epam.jwd.testingApp.controller.commands.CommandAccessLevel;
import by.epam.jwd.testingApp.controller.commands.CommandName;
import by.epam.jwd.testingApp.controller.commands.CommandProvider;
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

    private FilterConfig config = null;
    private boolean isActive = false;

    public static final String IS_ACTIVE_PARAM = "active";
    public static final String VALUE_FOR_ACTIVE = "TRUE";
    public static final String USER_ACCESS_LACK = "user.accessLack";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        String act = config.getInitParameter(IS_ACTIVE_PARAM);
        if (act != null){
            isActive = (act.toUpperCase().equals(VALUE_FOR_ACTIVE));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if(isActive){
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

            try {
                Parser<String> languageParser = new LanguageParser();
                String language = languageParser.parsing(request);
                ErrorMsgSupplier errorMsg = ErrorMsgProvider.newInstance().getManagerByLocale(language);
                request.setAttribute(AttributeNames.ERROR_MSG, errorMsg.getValueByName(USER_ACCESS_LACK));

                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response, PageMapping.AUTHORIZATION_PAGE);
            } catch (ServiceException e) {
                throw new ServletException(e);
            }

        }
    }

    @Override
    public void destroy() {
    }
}
