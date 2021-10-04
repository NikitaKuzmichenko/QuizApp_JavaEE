package by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.parametersParsers.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageParser implements Parser<String> {

    public static final String DEFAULT_LANGUAGE = "ru";
    public static final String ENGLISH_LANGUAGE = "en";
    public static final String RUSSIAN_LANGUAGE = "ru";
    @Override
    public String parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        String parameter = request.getParameter(AttributeNames.LANGUAGE);

        if(parameter!=null){
            session.setAttribute(AttributeNames.LANGUAGE, parameter);
            return parameter;
        }

        Object attribute = session.getAttribute(AttributeNames.LANGUAGE);
        if(attribute != null) {
            return attribute.toString();
        }

        session.setAttribute(AttributeNames.LANGUAGE, DEFAULT_LANGUAGE);
        return DEFAULT_LANGUAGE;
    }
}