package by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TestIdParser implements Parser<Integer> {

    @Override
    public Integer parsing(HttpServletRequest request) {

        String testId  = request.getParameter(AttributeNames.TEST_ID);
        if(testId != null) {
            return Integer.parseInt(testId);
        }
        Object sessionTestId = request.getSession().getAttribute(AttributeNames.TEST_ID);
        if(sessionTestId!=null){
            return Integer.parseInt(sessionTestId.toString());
        }
        return null;
    }
}
