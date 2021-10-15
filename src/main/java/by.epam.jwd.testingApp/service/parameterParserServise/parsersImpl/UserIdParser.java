package by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserIdParser implements Parser<Integer> {
    @Override
    public Integer parsing(HttpServletRequest request){
        HttpSession session = request.getSession();

        Object attribute = session.getAttribute(AttributeNames.USER_ID);
        if(attribute != null) {
            return Integer.parseInt(attribute.toString());
        }
        return null;
    }
}
