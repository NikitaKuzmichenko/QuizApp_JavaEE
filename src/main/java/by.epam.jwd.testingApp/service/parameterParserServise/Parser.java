package by.epam.jwd.testingApp.service.parameterParserServise;

import by.epam.jwd.testingApp.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Parser<T> {
    public T parsing(HttpServletRequest request) throws ServletException, IOException, ServiceException;
}
