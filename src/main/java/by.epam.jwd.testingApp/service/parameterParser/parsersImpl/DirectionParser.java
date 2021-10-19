package by.epam.jwd.testingApp.service.parameterParser.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParser.Parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DirectionParser implements Parser<Boolean> {

    public static final boolean UP = true;
    public static final boolean DOWN = false;

    @Override
    public Boolean parsing(HttpServletRequest request){

        String parameter = request.getParameter(AttributeNames.SORT_DIRECTION);

        if(parameter!=null){
            return Boolean.parseBoolean(parameter);
        }

        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(AttributeNames.SORT_DIRECTION);
        if(attribute!=null) {
            return Boolean.parseBoolean(attribute.toString());
        }

        return UP;
    }
}
