package by.epam.jwd.testingApp.controller.transitionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransitionByRedirect {

    public void doTransition(HttpServletRequest request, HttpServletResponse response,String path)
            throws IOException {
        response.sendRedirect(path);
    }
}
