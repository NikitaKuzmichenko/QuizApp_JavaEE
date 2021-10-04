package by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.parametersParsers.ParserConstants;
import by.epam.jwd.testingApp.controller.parametersParsers.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestsNumberParser implements Parser<Integer> {

    @Override
    public Integer parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        String parameter = request.getParameter(AttributeNames.CATEGORY);

        if(parameter!=null){
            session.setAttribute(AttributeNames.CATEGORY, parameter);
            session.setAttribute(AttributeNames.PAGE_NUMBER, ParserConstants.STARTING_PAGE);
            if(parameter.equals(ParserConstants.ALL_CATEGORIES)){
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(null);
            }else {
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(Integer.parseInt(parameter));
            }
        }

        String attribute = (String) session.getAttribute(AttributeNames.CATEGORY);
        if(attribute!=null) {
            if(attribute.equals(ParserConstants.ALL_CATEGORIES)){
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(null);
            }else {
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(Integer.parseInt(attribute));
            }
        }

        session.setAttribute(AttributeNames.PAGE_NUMBER, ParserConstants.STARTING_PAGE);
        return EntitiesServiceFactory.getInstance().getTestService(). // all Tests
                calculateTotalTestsNumber(null);
    }
}
