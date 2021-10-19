package by.epam.jwd.testingApp.service.parameterParser.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParser.Parser;

import javax.servlet.http.HttpServletRequest;

public class QuestionIdParser implements Parser<Integer> {
    @Override
    public Integer parsing(HttpServletRequest request){

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
