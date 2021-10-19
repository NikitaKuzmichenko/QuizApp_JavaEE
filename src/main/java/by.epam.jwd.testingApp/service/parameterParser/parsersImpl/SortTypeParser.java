package by.epam.jwd.testingApp.service.parameterParser.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParser.Parser;
import by.epam.jwd.testingApp.service.testSortingService.TestsSortingConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SortTypeParser implements Parser<String> {

    @Override
    public String parsing(HttpServletRequest request){

        String parameter = request.getParameter(AttributeNames.SORT_TYPE);

        if(parameter!=null){
            return parameter;
        }
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(AttributeNames.SORT_TYPE);
        if(attribute != null) {
            return attribute.toString();
        }

        return TestsSortingConstants.DATE_SORT;
    }
}
