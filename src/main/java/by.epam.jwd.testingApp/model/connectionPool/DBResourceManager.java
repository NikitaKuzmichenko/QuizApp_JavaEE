package by.epam.jwd.testingApp.model.connectionPool;


import java.util.ResourceBundle;

public class DBResourceManager {

    private static final String source = "dataBase";

    private static ResourceBundle bundle;

    private DBResourceManager(){
        bundle = ResourceBundle.getBundle(source);
    }

    private static class SingletonHolder {
        public static final DBResourceManager HOLDER_INSTANCE = new DBResourceManager();
    }

    public static DBResourceManager getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public String getValueByName(String name){
        return bundle.getString(name);
    }

}
