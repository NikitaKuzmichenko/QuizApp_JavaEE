package by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageParser implements Parser<String> {

    public static final String DEFAULT_LANGUAGE = "ru";
    @Override
    public String parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {

        String parameter = request.getParameter(AttributeNames.LANGUAGE);

        if(parameter!=null){
            return parameter;
        }

        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(AttributeNames.LANGUAGE);
        if(attribute != null) {
            return attribute.toString();
        }

        return DEFAULT_LANGUAGE;
    }
}
