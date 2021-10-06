package by.epam.jwd.testingApp.service.errorMsg;

import java.util.HashMap;
import java.util.Map;

public class ErrorMsgProvider {

    private static ErrorMsgProvider instance;

    private Map<String, ErrorMsgSupplier> managerMap;

    public static final  String DEFAULT_LANGUAGE = "RU";

    private ErrorMsgProvider(){
        managerMap = new HashMap<>();
        managerMap.put("RU",new RuErrorMsgSupplier());
        managerMap.put("EN",new EnErrorMsgSupplier());
    }

    public static ErrorMsgProvider newInstance() {
        ErrorMsgProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (ErrorMsgProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ErrorMsgProvider();
                }
            }
        }
        return localInstance;
    }

    public ErrorMsgSupplier getManagerByLocale(String locale){
        if(locale == null) return managerMap.get(DEFAULT_LANGUAGE);
        return managerMap.get(locale.toUpperCase());
    }

}
