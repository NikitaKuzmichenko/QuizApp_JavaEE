package by.epam.jwd.testingApp.controller.mapping;

public final class AttributeNames {
    // input data mapping from request
    public static final String PAGE_NUMBER = "num";
    public static final String CATEGORY = "category";
    public static final String SORT_DIRECTION = "direction";
    public static final String SORT_TYPE = "sortType";
    public static final String LANGUAGE = "language";

    public static final String USER_ROLE = "userRole";

    public static final String USER_ID = "userID";
    public static final String REMEMBER_USER = "remember";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String NICK_NAME = "nickName";

    // output data mapping to request
    public static final String NEW_TESTS = "newTests";
    public static final String POPULAR_TESTS = "popularTests";
    public static final String TEST_LIST = "tests";
    public static final String TEST_CREATORS = "users";
    public static final String TEST_RESULTS = "results";

    public static final String PAGINATION = "paginationList";
    public static final String CATEGORIES = "categories";
    public static final String ERROR_MSG = "errorMsg";

    private AttributeNames(){}
}
