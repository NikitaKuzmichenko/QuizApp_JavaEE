package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.parametersParsers.Parser;
import by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl.CategoryParser;
import by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl.DirectionParser;
import by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl.PageNumberParser;
import by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl.SortTypeParser;
import by.epam.jwd.testingApp.controller.parametersParsers.parsersImpl.TestsNumberParser;
import by.epam.jwd.testingApp.controller.sideBar.SideBarCreator;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.TestSortingService.TestsSorter;
import by.epam.jwd.testingApp.service.TestSortingService.TestsSorterProvider;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.pagination.AbstractPagination;
import by.epam.jwd.testingApp.service.pagination.DirectPagination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

            Parser<Integer> categoryParser = new CategoryParser();
            Parser<Integer> pageNumberParser = new PageNumberParser();
            Parser<Integer> testsNumberParser = new TestsNumberParser();
            Parser<Boolean> directionParser = new DirectionParser();
            Parser<String> sortTypeParser = new SortTypeParser();

            Integer categoryId = categoryParser.parsing(request);
            int pageNumber = pageNumberParser.parsing(request);
            int testsNumber = testsNumberParser.parsing(request);
            boolean direction = directionParser.parsing(request);
            String sortType = sortTypeParser.parsing(request);
            TestsSorter sorter = TestsSorterProvider.newInstance().getBySortType(sortType);
            List<Test> testList = sorter.doSorting(categoryId,pageNumber*LIMIT_ON_PAGE,direction,LIMIT_ON_PAGE);

            request.setAttribute(AttributeNames.USER_ROLE, 1);// remove

            AbstractPagination pagination = new DirectPagination();
            SideBarCreator.newInstance().create(request);

            request.setAttribute("tests",
                    testList);
            request.setAttribute("users",
                    factory.getUserService().selectTestCreators(testList));
            request.setAttribute("results",
                    factory.getResultService().calculateAvgResultsByTestId(testList));
            request.setAttribute("paginationList",
                    pagination.calculatePagination(pageNumber,testsNumber,LIMIT_ON_PAGE,PAGINATION_MAX_SIZE));
            request.setAttribute("categories", factory.getCategoryService().selectAll());

            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response,"pageContent");

        } catch (ServiceException e) {
            // call error page
        }

    }
}
