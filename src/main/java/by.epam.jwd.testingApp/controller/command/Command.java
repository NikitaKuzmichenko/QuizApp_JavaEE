package by.epam.jwd.testingApp.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    String POST_METHOD = "POST";
    String GET_METHOD = "GET";
    void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
