package by.epam.jwd.testingApp.model.connectionPool;

import java.util.ResourceBundle;

public class DBResourceManager {

    private static final String source = "D:\\IDEA\\webApp\\src\\main\\resources\\dataBase.properties";

    private static DBResourceManager instance;
    private static ResourceBundle bundle;

    private DBResourceManager(){}

    public static DBResourceManager newInstance() {
        if(instance == null) {
            bundle = ResourceBundle.getBundle(source);
            instance = new DBResourceManager();
        }
        return instance;
    }

    public String getValueByName(String name){
        return bundle.getString(name);
    }

}
