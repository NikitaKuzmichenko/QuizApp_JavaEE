package by.epam.jwd.testingApp.service.pagination;

import java.util.List;

public interface AbstractPagination {
    List<Integer> calculatePagination(int currentPage, int unitNumber, int limitOnPage, int paginationMaxSize);
}
