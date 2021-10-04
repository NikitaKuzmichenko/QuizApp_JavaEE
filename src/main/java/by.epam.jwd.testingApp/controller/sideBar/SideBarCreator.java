package by.epam.jwd.testingApp.controller.sideBar;

import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractTestService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.TestService;

import javax.servlet.http.HttpServletRequest;

public class SideBarCreator {

    private final AbstractTestService testService;
    private static SideBarCreator instance;

    public static final int LIMIT_ON_SIDE_BAR = 3;
    public static final int DEFAULT_OFFSET = 0;
    public static final boolean DEFAULT_DIRECTION = true;

    private SideBarCreator(){
        testService = new TestService();
    }

    public static SideBarCreator newInstance() {
        SideBarCreator localInstance = instance;
        if (localInstance == null) {
            synchronized (SideBarCreator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SideBarCreator();
                }
            }
        }
        return localInstance;
    }

    public void create(HttpServletRequest request) throws ServiceException {

        request.setAttribute("newTests",
                testService.sortByCreationDate(DEFAULT_OFFSET, DEFAULT_DIRECTION, LIMIT_ON_SIDE_BAR));
        request.setAttribute("popularTests",
                testService.sortByUsersPassedNumber(DEFAULT_OFFSET, DEFAULT_DIRECTION, LIMIT_ON_SIDE_BAR));
    }
}
