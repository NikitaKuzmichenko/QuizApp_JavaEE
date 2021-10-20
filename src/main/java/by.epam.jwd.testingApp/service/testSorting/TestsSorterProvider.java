package by.epam.jwd.testingApp.service.testSorting;

import java.util.HashMap;
import java.util.Map;

public class TestsSorterProvider {

    private static Map<String, TestsSorter> sorterMap;

    private TestsSorterProvider(){
        sorterMap = new HashMap<>();
        sorterMap.put(TestsSortingConstants.NAME_SORT,new TestsSorterByName());
        sorterMap.put(TestsSortingConstants.DATE_SORT,new TestsSorterByDate());
        sorterMap.put(TestsSortingConstants.POPULARITY_SORT,new TestsSorterByUsersPassed());
    }

    private static class SingletonHolder {
        public static final TestsSorterProvider HOLDER_INSTANCE = new TestsSorterProvider();
    }

    public static TestsSorterProvider getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public TestsSorter getBySortType(String sortType){
        return sorterMap.get(sortType);
    }

}
