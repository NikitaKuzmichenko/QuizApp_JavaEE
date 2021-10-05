package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.CategoryParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.DirectionParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.PageNumberParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.SortTypeParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.TestsNumberParser;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.testSortingService.TestsSorter;
import by.epam.jwd.testingApp.service.testSortingService.TestsSorterProvider;
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

            if(pageNumber > testsNumber/LIMIT_ON_PAGE){
                pageNumber = testsNumber/LIMIT_ON_PAGE;
            }else if(pageNumber < 0){
                pageNumber = 0;
            }

            boolean direction = directionParser.parsing(request);
            String sortType = sortTypeParser.parsing(request);
            TestsSorter sorter = TestsSorterProvider.newInstance().getBySortType(sortType);
            List<Test> testList = sorter.doSorting(categoryId,pageNumber*LIMIT_ON_PAGE,direction,LIMIT_ON_PAGE);

            AbstractPagination pagination = new DirectPagination();

            request.setAttribute(AttributeNames.TEST_LIST,
                    testList);
            request.setAttribute(AttributeNames.TEST_CREATORS,
                    factory.getUserService().selectTestCreators(testList));
            request.setAttribute(AttributeNames.TEST_RESULTS,
                    factory.getResultService().calculateAvgResultsByTestId(testList));
            request.setAttribute(AttributeNames.PAGINATION,
                    pagination.calculatePagination(pageNumber,testsNumber,LIMIT_ON_PAGE,PAGINATION_MAX_SIZE));
            request.setAttribute(AttributeNames.CATEGORIES, factory.getCategoryService().selectAll());

            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.WELCOME_PAGE);

        } catch (ServiceException e) {
            // redirect to error page
        }

    }
}
