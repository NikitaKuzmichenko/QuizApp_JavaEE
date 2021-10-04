package by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.parametersParsers.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DirectionParser implements Parser<Boolean> {

    public static final String UP ="true";
    public static final String DOWN ="false";

    @Override
    public Boolean parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {

        HttpSession session = request.getSession();
        String parameter = request.getParameter(AttributeNames.SORT_DIRECTION);

        if(parameter!=null){
            session.setAttribute(AttributeNames.SORT_DIRECTION,parameter);
            return Boolean.parseBoolean(parameter);
        }

        String attribute = (String) session.getAttribute(AttributeNames.SORT_DIRECTION);
        if(attribute!=null) {
            return Boolean.parseBoolean(attribute);
        }

        session.setAttribute(AttributeNames.SORT_DIRECTION, UP);
        return true;
    }
}
