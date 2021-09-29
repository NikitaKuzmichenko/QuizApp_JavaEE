package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.factory.ServiceFactory;
import by.epam.jwd.testingApp.service.serviceImpl.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToWelcomePage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            AbstractTestService testService = ServiceFactory.getInstance().getTestService();// fill sideBar
            request.setAttribute("newTests", testService.sortByCreationDate(0, true));
            request.setAttribute("popularTests", testService.sortByUsersPassedNumber(0, true));

            request.setAttribute("content", "welcomePageContent"); // main content jsp name


            request.getRequestDispatcher("/welcomePage.jsp").forward(request,response);

        } catch (ServiceException | IOException | ServletException e) {
            // call error page
        }

    }
}
