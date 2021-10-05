package by.epam.jwd.testingApp.service.filters;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.TestService;

import javax.servlet.*;
import java.io.IOException;

public class SideBarCreator implements Filter {

    private static AbstractTestService testService;

    private FilterConfig config = null;
    private boolean isActive = false;

    public static final String isActiveParam = "active";
    public static final String ValueForActive = "TRUE";

    public static final int LIMIT_ON_SIDE_BAR = 3;
    public static final int DEFAULT_OFFSET = 0;
    public static final boolean DEFAULT_DIRECTION = true;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        testService =  EntitiesServiceFactory.getInstance().getTestService();
        config = filterConfig;
        String act = config.getInitParameter(isActiveParam);
        if (act != null){
            isActive = (act.toUpperCase().equals(ValueForActive));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if(isActive){
            try {
                servletRequest.setAttribute(AttributeNames.NEW_TESTS,
                        testService.sortByCreationDate(DEFAULT_OFFSET, DEFAULT_DIRECTION, LIMIT_ON_SIDE_BAR));
                servletRequest.setAttribute(AttributeNames.POPULAR_TESTS,
                        testService.sortByUsersPassedNumber(DEFAULT_OFFSET, DEFAULT_DIRECTION, LIMIT_ON_SIDE_BAR));
            } catch (ServiceException e) {
                // redirect to error page
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
