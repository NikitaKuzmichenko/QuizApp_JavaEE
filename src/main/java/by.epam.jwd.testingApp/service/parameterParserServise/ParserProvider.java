package by.epam.jwd.testingApp.service.parameterParserServise;

import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.CategoryParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.DirectionParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.LanguageParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.PageNumberParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.SortTypeParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.TestsNumberParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.UserIdParser;

public class ParserProvider {

    private static ParserProvider instance;

    private Parser<Integer> userIdParser;
    private Parser<Integer> testNumberParser;
    private Parser<Integer> pageNumberParser;
    private Parser<Boolean> sortDirectionParser;
    private Parser<Integer> categoryParser;
    private Parser<String> languageParser;
    private Parser<String> sortTypeParser;

    private ParserProvider(){
        userIdParser = new UserIdParser();
        testNumberParser = new TestsNumberParser();
        pageNumberParser = new PageNumberParser();
        sortDirectionParser = new DirectionParser();
        categoryParser = new CategoryParser();
        languageParser = new LanguageParser();
        sortTypeParser = new SortTypeParser();
    }

    public static ParserProvider newInstance() {
        ParserProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (ParserProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ParserProvider();
                }
            }
        }
        return localInstance;
    }

    public Parser<Integer> getUserIdParser() {
        return userIdParser;
    }

    public Parser<Integer> getTestNumberParser() {
        return testNumberParser;
    }

    public Parser<Integer> getPageNumberParser() {
        return pageNumberParser;
    }

    public Parser<Boolean> getSortDirectionParser() {
        return sortDirectionParser;
    }

    public Parser<Integer> getCategoryParser() {
        return categoryParser;
    }

    public Parser<String> getLanguageParser() {
        return languageParser;
    }

    public Parser<String> getSortTypeParser() {
        return sortTypeParser;
    }
}
