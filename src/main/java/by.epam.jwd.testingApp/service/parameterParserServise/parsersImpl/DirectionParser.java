package by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DirectionParser implements Parser<Boolean> {

    public static final boolean UP = true;
    public static final boolean DOWN = false;

    @Override
    public Boolean parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {

        String parameter = request.getParameter(AttributeNames.SORT_DIRECTION);

        if(parameter!=null){
            return Boolean.parseBoolean(parameter);
        }

        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(AttributeNames.SORT_DIRECTION);
        if(attribute!=null) {
            return Boolean.parseBoolean(attribute.toString());
        }

        return UP;
    }
}
