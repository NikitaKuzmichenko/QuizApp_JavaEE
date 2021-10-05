package by.epam.jwd.testingApp.service.filters;

import by.epam.jwd.testingApp.controller.cookieManager.CookieManager;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.entities.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractUserService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.UserService;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.UserIdParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private FilterConfig config = null;
    private boolean isActive = false;

    public static final String isActiveParam = "active";
    public static final String ValueForActive = "TRUE";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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
                HttpServletRequest request = (HttpServletRequest) servletRequest;

                Parser<Integer> userIdParser = new UserIdParser();
                Integer userId = userIdParser.parsing(request);

                if(userId == null){ // not specified in session -> check cookies
                    CookieManager cookieManager = CookieManager.getInstance();
                    String temp = cookieManager.findValueByName(request,AttributeNames.USER_ID);
                    if(temp == null) {
                        userId=null;
                    }else{
                        userId = Integer.parseInt(temp);
                    }
                }

                AbstractUserService userService = new UserService();
                User user = userService.selectEntityById(userId);
                if(user == null){
                    System.out.println("roleId = null");
                    servletRequest.setAttribute(AttributeNames.USER_ROLE, null);
                }
                else{
                    System.out.println("roleId = " +  user.getRoleId());
                    System.out.println("name = " +  user.getName());
                    servletRequest.setAttribute(AttributeNames.USER_ROLE, user.getRoleId());
                    servletRequest.setAttribute(AttributeNames.NICK_NAME, user.getName());
                }
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
