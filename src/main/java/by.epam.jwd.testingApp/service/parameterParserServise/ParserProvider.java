package by.epam.jwd.testingApp.service.parameterParserServise;

import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.CategorizedTestsNumberParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.CategoryParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.DirectionParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.LanguageParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.PageNumberParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.PassingTestParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.QuestionIdParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.SortTypeParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.TestIdParser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.UserIdParser;

public class ParserProvider {

    private static ParserProvider instance;

    private Parser<Integer> userIdParser;
    private Parser<Integer> testNumberParser;
    private Parser<Integer> pageNumberParser;
    private Parser<Integer> categoryParser;
    private Parser<Integer> testIdParser;
    private Parser<Integer> questionIdParser;
    private Parser<Integer> passingTestParser;
    private Parser<Boolean> sortDirectionParser;
    private Parser<String> languageParser;
    private Parser<String> sortTypeParser;

    private ParserProvider(){
        userIdParser = new UserIdParser();
        testNumberParser = new CategorizedTestsNumberParser();
        pageNumberParser = new PageNumberParser();
        sortDirectionParser = new DirectionParser();
        categoryParser = new CategoryParser();
        languageParser = new LanguageParser();
        sortTypeParser = new SortTypeParser();
        testIdParser = new TestIdParser();
        questionIdParser = new QuestionIdParser();
        passingTestParser = new PassingTestParser();
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

    public Parser<Integer> getPassingTestParser() {
        return passingTestParser;
    }

    public Parser<Integer> getQuestionIdParser() {
        return questionIdParser;
    }

    public Parser<Integer> getTestIdParser() {
        return testIdParser;
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
