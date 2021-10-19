package by.epam.jwd.testingApp.service.parameterParser.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParser.Parser;

import javax.servlet.http.HttpServletRequest;

public class PassingTestParser implements Parser<Integer> {

    @Override
    public Integer parsing(HttpServletRequest request) {
        String testId  = request.getParameter(AttributeNames.PASSING_TEST);
        if(testId != null) {
            return Integer.parseInt(testId);
        }

        Object sessionTestId = request.getSession().getAttribute(AttributeNames.PASSING_TEST);
        if(sessionTestId!=null){
            return Integer.parseInt(sessionTestId.toString());
        }

        return null;
    }
}
