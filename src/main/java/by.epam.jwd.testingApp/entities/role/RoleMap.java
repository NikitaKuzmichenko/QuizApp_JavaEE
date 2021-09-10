package by.epam.jwd.testingApp.entities.role;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RoleMap {
    private static final Map<Role,String> roles = new HashMap<>();
    static {
        //categories.put(new Category("role1"), "ROLE1"); for example
    }

    public static String findRoleName(String categoryName){
        return  roles.get(new Role(categoryName));
    }

    public static Iterator<Map.Entry<Role,String>> MapIterator(){
        return roles.entrySet().iterator();
    }
}
