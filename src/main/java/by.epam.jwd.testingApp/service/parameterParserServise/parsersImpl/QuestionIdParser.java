package by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class QuestionIdParser implements Parser<Integer> {
    @Override
    public Integer parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {

        String id = request.getParameter(AttributeNames.QUESTION_ID);
        if(id!=null){
            return Integer.parseInt(id);
        }

        Object object = request.getSession().getAttribute(AttributeNames.QUESTION_ID);
        if(object!=null){
            return Integer.parseInt(object.toString());
        }

        return null;
    }
}
