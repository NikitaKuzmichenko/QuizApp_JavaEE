package by.epam.jwd.testingApp.controller.transitionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Transition {

    void doTransition(HttpServletRequest request, HttpServletResponse response, String contentJsp)
            throws ServletException, IOException;
}
