package by.epam.jwd.testingApp.controller.mapping;

public final class PageMapping {
    public static final String AUTHORIZATION_PAGE = "authorizationPage";
    public static final String TO_AUTHORIZATION_PAGE_PATH = "controller/authorization";

    public static final String REGISTRATION_PAGE = "registrationPage";

    public static final String WELCOME_PAGE = "welcomePage";
    public static final String TO_WELCOME_PAGE_PATH = "controller/page?num=1";

    public static final String VIEW_MY_TESTS_PATH = "controller/view_my_tests";
    public static final String VIEW_MY_TESTS = "viewCreatedTestsPage";

    public static final String CREATE_TESTS_PATH = "controller/create_test";
    public static final String CREATE_TESTS = "createTestPage";

    public static final String EDIT_TESTS_PATH = "controller/edit_test";
    public static final String EDIT_TESTS = "editTestPage";

    public static final String ADD_QUESTION_PATH = "controller/add_question";
    public static final String ADD_QUESTION = "addQuestionPage";

    public static final String EDIT_QUESTION_PATH = "controller/edit_question";
    public static final String EDIT_QUESTION = "editQuestionPage";

    private PageMapping(){}
}
