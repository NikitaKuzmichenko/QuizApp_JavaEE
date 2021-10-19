package by.epam.jwd.testingApp.service.parameterParser.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParser.Parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PageNumberParser implements Parser<Integer> {

    public final static int STARTING_PAGE = 1;
    @Override
    public Integer parsing(HttpServletRequest request) {

        String parameter = request.getParameter(AttributeNames.PAGE_NUMBER);

        if(parameter!=null){
            return Integer.parseInt(parameter);
        }

        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(AttributeNames.PAGE_NUMBER);
        if(attribute != null) {
            return Integer.parseInt(attribute.toString());
        }
        return STARTING_PAGE;
    }
}
