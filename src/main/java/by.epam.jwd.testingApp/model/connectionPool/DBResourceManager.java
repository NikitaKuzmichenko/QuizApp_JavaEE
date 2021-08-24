package by.epam.jwd.testingApp.model.connectionPool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static DBResourceManager instance;
    private static ResourceBundle bundle;

    private DBResourceManager(){}

    public static DBResourceManager newInstance() {
        if(instance == null) {
            bundle = ResourceBundle.getBundle("dataBase");
            instance = new DBResourceManager();
        }
        return instance;
    }

    public String getValueByName(String name){
        return bundle.getString(name);
    }

}
