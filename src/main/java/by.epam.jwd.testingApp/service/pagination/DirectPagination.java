package by.epam.jwd.testingApp.service.pagination;

import java.util.LinkedList;
import java.util.List;

public class DirectPagination{

    public static final int ELLIPSIS_CODE = -1;
    public static final int FIRST_ELEMENT = 1;

    private static class SingletonHolder {
        public static final DirectPagination HOLDER_INSTANCE = new DirectPagination();
    }

    public static DirectPagination getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public List<Integer> calculatePagination(int currentPage, int unitNumber,int limitOnPage,int paginationMaxSize) {

        List<Integer> result = new LinkedList<>();
        if(currentPage < 0 || unitNumber < 0 || limitOnPage <= 0 || paginationMaxSize <=0) return result;

        int pagesNumber = (int)Math.ceil((double)unitNumber/limitOnPage);
        if(pagesNumber <= 1 )return result;

        if(pagesNumber < paginationMaxSize){
            for(int i = 0; i < pagesNumber; i++){
                result.add(i + 1);
            }
            if(result.size() == 1){
                result.clear();
            }
            return  result;
        }

        if(currentPage > pagesNumber - paginationMaxSize/2 - 2){
            result.add(1);
            result.add(ELLIPSIS_CODE);
            for(int i = paginationMaxSize; i > 1; i--){
                result.add(pagesNumber - i + 2);
            }
            return  result;
        }

        if(currentPage < paginationMaxSize/2 + 2){
            for(int i = 0; i < paginationMaxSize; i++){
                result.add(i + 1);
            }
            result.add(ELLIPSIS_CODE);
            result.add(pagesNumber);
            return  result;
        }

        // no more options left. its {1 ... x x x currentPage x x x ... pagesNumber} format
        result.add(FIRST_ELEMENT);
        result.add(ELLIPSIS_CODE);

        for(int i = paginationMaxSize/2; i > 0; i--){
            result.add(currentPage - i + 1);
        }
        result.add(currentPage + 1);

        for(int i = 0; i < paginationMaxSize/2; i++){
            result.add(currentPage + i + 2);
        }

        result.add(ELLIPSIS_CODE);
        result.add(pagesNumber);

        return  result;
    }
}
