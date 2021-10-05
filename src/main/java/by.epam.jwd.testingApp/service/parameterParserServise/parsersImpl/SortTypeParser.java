package by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.testSortingService.TestsSortingConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SortTypeParser implements Parser<String> {

    @Override
    public String parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {

        HttpSession session = request.getSession();
        String parameter = request.getParameter(AttributeNames.SORT_TYPE);

        if(parameter!=null){
            session.setAttribute(AttributeNames.SORT_TYPE, parameter);
            return parameter;
        }

        String attribute = (String) session.getAttribute(AttributeNames.SORT_TYPE);
        if(attribute != null) {
            return attribute;
        }

        session.setAttribute(AttributeNames.SORT_TYPE, TestsSortingConstants.DATE_SORT);
        return TestsSortingConstants.DATE_SORT;
    }
}
