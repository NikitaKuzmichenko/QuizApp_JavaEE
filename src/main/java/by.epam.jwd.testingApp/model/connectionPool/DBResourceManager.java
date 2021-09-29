package by.epam.jwd.testingApp.model.connectionPool;


import java.util.ResourceBundle;

public class DBResourceManager {

    private static final String source = "dataBase";

    private static DBResourceManager instance;
    private static ResourceBundle bundle;

    private DBResourceManager(){}

    public static DBResourceManager newInstance() {
        DBResourceManager localInstance = instance;
        if (localInstance == null) {
            synchronized (DBResourceManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    bundle = ResourceBundle.getBundle(source);
                    instance = localInstance = new DBResourceManager();
                }
            }
        }
        return localInstance;
    }

    public String getValueByName(String name){
        return bundle.getString(name);
    }

}
