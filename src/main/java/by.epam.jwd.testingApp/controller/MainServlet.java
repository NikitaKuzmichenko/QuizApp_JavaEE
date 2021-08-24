package by.epam.jwd.testingApp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebServlet("/")
public class MainServlet extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        message = "This is simple servlet message";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);

        //response.setContentType("text/html");
       // PrintWriter messageWriter = response.getWriter();
        //messageWriter.println("<h1>" + message + "<h1>");
    }

    public void destroy() {}

}
