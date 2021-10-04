package by.epam.jwd.testingApp.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    int LIMIT_ON_PAGE = 5;
    int PAGINATION_MAX_SIZE = 7;

    void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
