package by.epam.jwd.testingApp.service.parameterParser.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParser.Parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CategoryParser implements Parser<Integer> {
    public final static String ALL_CATEGORIES = "all";
    @Override
    public Integer parsing(HttpServletRequest request)  {

        HttpSession session = request.getSession();
        String parameter = request.getParameter(AttributeNames.CATEGORY);
        Object attribute = session.getAttribute(AttributeNames.CATEGORY);

        if(parameter!=null){
            if(parameter.equals(ALL_CATEGORIES)) {
                return null;
            }
            else {
                return Integer.parseInt(parameter);
            }
        }

        if(attribute!=null) {
            if(attribute.equals(ALL_CATEGORIES)) {
                return null;
            }
            else {
                return Integer.parseInt(attribute.toString());
            }
        }

        return null;
    }
}
