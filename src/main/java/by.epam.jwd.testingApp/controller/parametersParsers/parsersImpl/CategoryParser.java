package by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.parametersParsers.ParserConstants;
import by.epam.jwd.testingApp.controller.parametersParsers.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CategoryParser implements Parser<Integer> {

    @Override
    public Integer parsing(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String parameter = request.getParameter(AttributeNames.CATEGORY);
        String attribute = (String) session.getAttribute(AttributeNames.CATEGORY);

        if(parameter!=null){
            if(attribute!= null && attribute.equals(parameter)){
                session.setAttribute(AttributeNames.PAGE_NUMBER, ParserConstants.STARTING_PAGE);
            }
            session.setAttribute(AttributeNames.CATEGORY,parameter);
            if(parameter.equals(ParserConstants.ALL_CATEGORIES)) {
                return null;
            }
            else {
                return Integer.parseInt(parameter);
            }
        }

        if(attribute!=null) {
            if(attribute.equals(ParserConstants.ALL_CATEGORIES)) {
                return null;
            }
            else {
                return Integer.parseInt(attribute);
            }
        }

        session.setAttribute(AttributeNames.CATEGORY, ParserConstants.ALL_CATEGORIES);
        // if no category information show all
        return null; // PK cant be null
    }
}
