package by.epam.jwd.testingApp.service.parameterParser.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParser.Parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
