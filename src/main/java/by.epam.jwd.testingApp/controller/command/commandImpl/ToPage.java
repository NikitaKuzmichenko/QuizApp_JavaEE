package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Test;
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

    public final static int STARTING_PAGE = 0;
    public static final int LIMIT_ON_PAGE = 5;
    public static final int PAGINATION_MAX_SIZE = 7;

    public final static String ALL_CATEGORIES = "all";

    private int calculateTestsNumber(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        String parameter = request.getParameter(AttributeNames.CATEGORY);

        if(parameter!=null){
            if(parameter.equals(ALL_CATEGORIES)){
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(null);
            }else {
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(Integer.parseInt(parameter));
            }
        }

        Object attribute =  session.getAttribute(AttributeNames.CATEGORY);
        if(attribute!=null) {
            if(attribute.equals(ALL_CATEGORIES)){
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(null);
            }else {
                return EntitiesServiceFactory.getInstance().getTestService().
                        calculateTotalTestsNumber(Integer.parseInt(attribute.toString()));
            }
        }

        return EntitiesServiceFactory.getInstance().getTestService(). // all Tests
                calculateTotalTestsNumber(null);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession();

        ParserProvider parserProvider = ParserProvider.getInstance();

        Integer categoryId = parserProvider.getCategoryParser().parsing(request);
        session.setAttribute(AttributeNames.CATEGORY,categoryId);

        boolean direction = parserProvider.getSortDirectionParser().parsing(request);
        session.setAttribute(AttributeNames.SORT_DIRECTION,direction);

        String sortType = parserProvider.getSortTypeParser().parsing(request);
        session.setAttribute(AttributeNames.SORT_TYPE, sortType);

        int pageNumber = parserProvider.getPageNumberParser().parsing(request);
        session.setAttribute(AttributeNames.PAGE_NUMBER, pageNumber);

        if(request.getParameter(AttributeNames.CATEGORY)!=null){
            session.setAttribute(AttributeNames.PAGE_NUMBER, STARTING_PAGE);
        }

        try {
            int testsNumber = calculateTestsNumber(request);
            if(pageNumber > (testsNumber-1)/LIMIT_ON_PAGE){
                pageNumber = (testsNumber-1)/LIMIT_ON_PAGE;
            }else if(pageNumber < 0){
                pageNumber = 0;
            }
            session.setAttribute(AttributeNames.PAGE_NUMBER, pageNumber);

            TestsSorter sorter = TestsSorterProvider.getInstance().getBySortType(sortType);
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

        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        TransitionManager.getInstance().getTransitionByForward().
                doTransition(request, response, PageMapping.WELCOME_PAGE);
    }
}
