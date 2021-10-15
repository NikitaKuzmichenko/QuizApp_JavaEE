package by.epam.jwd.testingApp.controller.command.commandImpl;

import by.epam.jwd.testingApp.controller.command.Command;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.controller.mapping.PageMapping;
import by.epam.jwd.testingApp.controller.transitionManager.TransitionManager;
import by.epam.jwd.testingApp.entity.Result;
import by.epam.jwd.testingApp.entity.Test;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.entitiesService.factory.EntitiesServiceFactory;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgProvider;
import by.epam.jwd.testingApp.service.errorMsg.ErrorMsgSupplier;
import by.epam.jwd.testingApp.service.pagination.DirectPagination;
import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewResults  implements Command {

    public static final String UNDEFINED_USER = "test.undefinedUser";

    public static final int LIMIT_ON_PAGE = 5;
    public static final int PAGINATION_MAX_SIZE = 7;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EntitiesServiceFactory factory = EntitiesServiceFactory.getInstance();
            HttpSession session = request.getSession();
            ParserProvider parserProvider = ParserProvider.newInstance();

            Integer userId = parserProvider.getUserIdParser().parsing(request);
            if(userId==null){
                String language = ParserProvider.newInstance().getLanguageParser().parsing(request);
                ErrorMsgSupplier errorMsg = ErrorMsgProvider.newInstance().getManagerByLocale(language);
                request.setAttribute(AttributeNames.ERROR_MSG, errorMsg.getValueByName(UNDEFINED_USER));

                TransitionManager.newInstance().getTransitionByForward().
                        doTransition(request, response, PageMapping.AUTHORIZATION_PAGE);
                return;
            }

            int testsNumber = factory.getResultService().calculateRowsNumberByTestId(userId);

            int pageNumber = parserProvider.getPageNumberParser().parsing(request);
            session.setAttribute(AttributeNames.PAGE_NUMBER, pageNumber + 1);

            List<Result> userResults = factory.getResultService().
                    selectByUserId(userId,pageNumber * LIMIT_ON_PAGE ,LIMIT_ON_PAGE);


            List<Test> testList = new ArrayList<>();
            List<Double> rating = new ArrayList<>();
            List<Date> dates = new ArrayList<>();
            Test test;

            AbstractTestService testService = factory.getTestService();
            for(Result result: userResults){
                test = testService.selectEntityById(result.getTestId());
                rating.add(result.getResult()/100d);
                dates.add(result.getPassingDate());
                if(test!=null){
                    testList.add(test);
                }
            }

            request.setAttribute(AttributeNames.DATES, dates);
            request.setAttribute(AttributeNames.TEST_RESULTS, rating);
            request.setAttribute(AttributeNames.TEST_LIST, testList);
            request.setAttribute(AttributeNames.PAGINATION,
                    DirectPagination.newInstance().
                            calculatePagination(pageNumber,testsNumber,LIMIT_ON_PAGE,PAGINATION_MAX_SIZE));

            TransitionManager.newInstance().getTransitionByForward().
                    doTransition(request, response, PageMapping.VIEW_RESULTS);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
