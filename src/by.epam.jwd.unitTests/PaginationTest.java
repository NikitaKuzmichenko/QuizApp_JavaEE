import by.epam.jwd.testingApp.service.pagination.DirectPagination;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class PaginationTest extends Assert {
    private static final DirectPagination pagination = DirectPagination.getInstance();

    @Test
    public void incorrectCurrentPagePaginationTest(){
        assertEquals(0,
                pagination.calculatePagination(-1,1,1,1).size());
    }

    @Test
    public void incorrectUnitNumberPaginationTest(){
        assertEquals(0,
                pagination.calculatePagination(1,-1,1,1).size());
    }

    @Test
    public void incorrectLimitOnPagePaginationTest(){
        assertEquals(0,
                pagination.calculatePagination(1,1,0,1).size());
    }

    @Test
    public void incorrectPaginationMaxSizeTest(){
        assertEquals(0,
                pagination.calculatePagination(1,1,0,1).size());
    }

    @Test
    public void onePagePaginationTest(){
        assertEquals(0,
                pagination.calculatePagination(1,2,2,3).size());
    }

    @Test
    public void correctPaginationTest(){
        List<Integer> expected = new LinkedList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        assertEquals(expected,
                pagination.calculatePagination(1,6,2,4));
    }

}
