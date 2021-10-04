package by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.parametersParsers.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserIdParser implements Parser<Integer> {
    @Override
    public Integer parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        String parameter = (String) session.getAttribute(AttributeNames.USER_ID);
        if(parameter!=null)
            return Integer.parseInt(parameter);
        return null;
    }
}
