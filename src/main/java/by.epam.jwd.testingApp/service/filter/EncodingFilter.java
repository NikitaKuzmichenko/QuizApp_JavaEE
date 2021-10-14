package by.epam.jwd.testingApp.service.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private FilterConfig config = null;
    private boolean isActive = false;

    public static final String IS_ACTIVE_PARAM = "active";
    public static final String VALUE_FOR_ACTIVE = "TRUE";
    public static final String CHAR_SET = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        String act = config.getInitParameter(IS_ACTIVE_PARAM);
        if (act != null){
            isActive = (act.toUpperCase().equals(VALUE_FOR_ACTIVE));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if(isActive){
            servletRequest.setCharacterEncoding(CHAR_SET);
            servletResponse.setCharacterEncoding(CHAR_SET);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
