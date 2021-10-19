package by.epam.jwd.testingApp.service.parameterParser;

import javax.servlet.http.HttpServletRequest;

public interface Parser<T> {
    public T parsing(HttpServletRequest request);
}
