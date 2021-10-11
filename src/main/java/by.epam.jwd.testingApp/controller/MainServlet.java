package by.epam.jwd.testingApp.controller;

import by.epam.jwd.testingApp.controller.commands.CommandProvider;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.model.connectionPool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MainServlet extends HttpServlet  {

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance().InitPool();
        super.init();
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().removeAllConnections();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        CommandProvider.getInstance().selectCommand(uri.substring(uri.lastIndexOf('/') + 1)).execute(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        CommandProvider.getInstance().selectCommand(uri.substring(uri.lastIndexOf('/') + 1)).execute(request,response);
    }


}
