package by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestsNumberParser implements Parser<Integer> {
    public final static String ALL_CATEGORIES = "all";
    @Override
    public Integer parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        String parameter = request.getParameter(AttributeNames.CATEGORY);

        if(parameter!=null){
            if(parameter.equals(ALL_CATEGORIES)){
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(null);
            }else {
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(Integer.parseInt(parameter));
            }
        }

        Object attribute =  session.getAttribute(AttributeNames.CATEGORY);
        if(attribute!=null) {
            if(attribute.equals(ALL_CATEGORIES)){
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(null);
            }else {
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(Integer.parseInt(attribute.toString()));
            }
        }

        return EntitiesServiceFactory.getInstance().getTestService(). // all Tests
                calculateTotalTestsNumber(null);
    }
}
