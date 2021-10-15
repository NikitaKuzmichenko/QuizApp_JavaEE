package by.epam.jwd.testingApp.service.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    public static final String CHAR_SET = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(CHAR_SET);
        servletResponse.setCharacterEncoding(CHAR_SET);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
