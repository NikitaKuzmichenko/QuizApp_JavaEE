package by.epam.jwd.testingApp.service.filter;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParser.Parser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.LanguageParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        Parser<String> languageParser = new LanguageParser();
        String language = languageParser.parsing(request);
        if(request.getParameter(AttributeNames.LANGUAGE)==null){
            request.setAttribute(AttributeNames.LANGUAGE,language);
        }else {
            request.getSession().setAttribute(AttributeNames.LANGUAGE, language);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
