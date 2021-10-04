package by.epam.jwd.testingApp.service.TestSortingService;

import java.util.HashMap;
import java.util.Map;

public class TestsSorterProvider {

    private static TestsSorterProvider instance;

    private Map<String, TestsSorter> sorterMap;

    private TestsSorterProvider(){
        sorterMap = new HashMap<>();
        sorterMap.put(TestsSortingConstants.NAME_SORT,new TestsSorterByName());
        sorterMap.put(TestsSortingConstants.DATE_SORT,new TestsSorterByDate());
        sorterMap.put(TestsSortingConstants.POPULARITY_SORT,new TestsSorterByUsersPassed());
    }

    public static TestsSorterProvider newInstance() {
        TestsSorterProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (TestsSorterProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TestsSorterProvider();
                }
            }
        }
        return localInstance;
    }

    public TestsSorter getBySortType(String sortType){
        return sorterMap.get(sortType);
    }

}
