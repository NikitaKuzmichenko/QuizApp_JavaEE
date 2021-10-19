package by.epam.jwd.testingApp.model.connectionPool;


import java.util.ResourceBundle;

public class DBResourceManager {

    private static final String DEFAULT_BUNDLE_NAME = "dataBase";

    private static String bundleName = DEFAULT_BUNDLE_NAME;

    private static ResourceBundle bundle;

    private DBResourceManager(){
        bundle = ResourceBundle.getBundle(bundleName);
    }

    private static class SingletonHolder {
        public static final DBResourceManager HOLDER_INSTANCE = new DBResourceManager();
    }

    public static DBResourceManager getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public static void setBundleName(String bundleName){
        if(bundleName!=null) {
            DBResourceManager.bundleName = bundleName;
        }
    }

    public String getValueByName(String name){
        return bundle.getString(name);
    }

}
