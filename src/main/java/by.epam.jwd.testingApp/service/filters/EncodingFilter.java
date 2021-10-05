package by.epam.jwd.testingApp.service.filters;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private FilterConfig config = null;
    private boolean isActive = false;

    public static final String isActiveParam = "active";
    public static final String ValueForActive = "TRUE";
    public static final String charSet = "UTF-8";

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
            servletRequest.setCharacterEncoding(charSet);
            servletResponse.setCharacterEncoding(charSet);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
