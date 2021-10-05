package by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserConstants;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageNumberParser implements Parser<Integer> {

    @Override
    public Integer parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {

        HttpSession session = request.getSession();
        String parameter = request.getParameter(AttributeNames.PAGE_NUMBER);

        if(parameter!=null){
            session.setAttribute(AttributeNames.PAGE_NUMBER, parameter);
            return Integer.parseInt(parameter)-1;
        }

        Object attribute = session.getAttribute(AttributeNames.PAGE_NUMBER);
        if(attribute != null) {
            return Integer.parseInt(attribute.toString()) - 1;
        }

        session.setAttribute(AttributeNames.PAGE_NUMBER, ParserConstants.STARTING_PAGE);
        return ParserConstants.STARTING_PAGE - 1;
    }
}
