package by.epam.jwd.testingApp.entities.category;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CategoryMap {
    private static final Map<Category,String> categories = new HashMap<>();
    static {
        //categories.put(new Category("category1"), "CATEGORY1"); for example
    }

    public static String findCategoryName(String categoryName){
        return  categories.get(new Category(categoryName));
    }

    public static Iterator<Map.Entry<Category,String>> MapIterator(){
        return categories.entrySet().iterator();
    }

}
