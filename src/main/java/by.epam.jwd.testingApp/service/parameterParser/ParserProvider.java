package by.epam.jwd.testingApp.service.parameterParser;

import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.CategoryParser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.DirectionParser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.LanguageParser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.PageNumberParser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.PassingTestParser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.QuestionIdParser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.SortTypeParser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.TestIdParser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.UserIdParser;

public class ParserProvider {

    private static Parser<Integer> userIdParser;
    private static Parser<Integer> pageNumberParser;
    private static Parser<Integer> categoryParser;
    private static Parser<Integer> testIdParser;
    private static Parser<Integer> questionIdParser;
    private static Parser<Integer> passingTestParser;
    private static Parser<Boolean> sortDirectionParser;
    private static Parser<String> languageParser;
    private static Parser<String> sortTypeParser;

    private ParserProvider(){
        userIdParser = new UserIdParser();
        pageNumberParser = new PageNumberParser();
        sortDirectionParser = new DirectionParser();
        categoryParser = new CategoryParser();
        languageParser = new LanguageParser();
        sortTypeParser = new SortTypeParser();
        testIdParser = new TestIdParser();
        questionIdParser = new QuestionIdParser();
        passingTestParser = new PassingTestParser();
    }

    private static class SingletonHolder {
        public static final ParserProvider HOLDER_INSTANCE = new ParserProvider();
    }

    public static ParserProvider getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
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
