package by.epam.jwd.testingApp.controller.transitionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransitionByForward  implements Transition{

    public static final String WRAPPER_JSP_NAME = "/wrapperPage.jsp";
    public static final String CONTENT_ATTRIBUTE_NAME = "content";

    @Override
    public void doTransition(HttpServletRequest request, HttpServletResponse response,String contentJsp)
            throws ServletException, IOException {

        request.setAttribute(CONTENT_ATTRIBUTE_NAME, contentJsp);
        request.getRequestDispatcher(WRAPPER_JSP_NAME).forward(request,response);
    }
}
