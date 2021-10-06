package by.epam.jwd.testingApp.service.filters;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.LanguageParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter{

    private FilterConfig config = null;
    private boolean isActive = false;

    public static final String IS_ACTIVE_PARAM = "active";
    public static final String VALUE_FOR_ACTIVE = "TRUE";

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
            try {
                HttpServletRequest request = (HttpServletRequest)servletRequest;
                Parser<String> languageParser = new LanguageParser();
                String language = languageParser.parsing(request);
                request.getSession().setAttribute(AttributeNames.LANGUAGE, language);
            } catch (ServiceException e) {
                // redirect to error page
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
