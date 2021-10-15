package by.epam.jwd.testingApp.service.errorMsg;

import java.util.HashMap;
import java.util.Map;

public class ErrorMsgProvider {

    private Map<String, ErrorMsgSupplier> managerMap;

    public static final  String DEFAULT_LANGUAGE = "RU";

    private ErrorMsgProvider(){
        managerMap = new HashMap<>();
        managerMap.put("RU",new RuErrorMsgSupplier());
        managerMap.put("EN",new EnErrorMsgSupplier());
    }

    private static class SingletonHolder {
        public static final ErrorMsgProvider HOLDER_INSTANCE = new ErrorMsgProvider();
    }

    public static ErrorMsgProvider getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public ErrorMsgSupplier getManagerByLocale(String locale){
        if(locale == null) return managerMap.get(DEFAULT_LANGUAGE);
        return managerMap.get(locale.toUpperCase());
    }

}
