package by.epam.jwd.testingApp.controller.commands.commandImpl;

import by.epam.jwd.testingApp.controller.commands.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entities.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.testSortingService.TestsSorter;
import by.epam.jwd.testingApp.service.testSortingService.TestsSorterProvider;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.pagination.DirectPagination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ToPage implements Command {

    public final static int STARTING_PAGE = 1;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            HttpSession session = request.getSession();

            ParserProvider parserProvider = ParserProvider.newInstance();

            Integer categoryId = parserProvider.getCategoryParser().parsing(request);
            session.setAttribute(AttributeNames.CATEGORY,categoryId);

            boolean direction = parserProvider.getSortDirectionParser().parsing(request);
            session.setAttribute(AttributeNames.SORT_DIRECTION,direction);

            String sortType = parserProvider.getSortTypeParser().parsing(request);
            session.setAttribute(AttributeNames.SORT_TYPE, sortType);

            int pageNumber = parserProvider.getPageNumberParser().parsing(request);
            session.setAttribute(AttributeNames.PAGE_NUMBER, pageNumber + 1);

            int testsNumber = parserProvider.getTestNumberParser().parsing(request);
            if(request.getParameter(AttributeNames.CATEGORY)!=null){
                session.setAttribute(AttributeNames.PAGE_NUMBER, STARTING_PAGE);
            }

            if(pageNumber > testsNumber/LIMIT_ON_PAGE){
                pageNumber = testsNumber/LIMIT_ON_PAGE;
            }else if(pageNumber < 0){
                pageNumber = 0;
            }

            TestsSorter sorter = TestsSorterProvider.newInstance().getBySortType(sortType);
            List<Test> testList = sorter.doSorting(categoryId,pageNumber*LIMIT_ON_PAGE,direction,LIMIT_ON_PAGE);

            EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();

            request.setAttribute(AttributeNames.TEST_LIST,
                    testList);
            request.setAttribute(AttributeNames.TEST_CREATORS,
                    factory.getUserService().selectTestCreators(testList));
            request.setAttribute(AttributeNames.TEST_RESULTS,
                    factory.getResultService().calculateAvgResultsByTestId(testList));
            request.setAttribute(AttributeNames.PAGINATION,
                    DirectPagination.newInstance().
                            calculatePagination(pageNumber,testsNumber,LIMIT_ON_PAGE,PAGINATION_MAX_SIZE));
            request.setAttribute(AttributeNames.CATEGORIES, factory.getCategoryService().selectAll());

            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.WELCOME_PAGE);

        } catch (ServiceException e) {
            throw new ServletException(e);
        }

    }
}
