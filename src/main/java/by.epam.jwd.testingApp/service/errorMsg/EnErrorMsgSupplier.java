package by.epam.jwd.testingApp.service.errorMsg;

import java.util.Locale;
import java.util.ResourceBundle;

public class EnErrorMsgSupplier implements ErrorMsgSupplier {

    private static ResourceBundle bundle;
    public static Locale locale = new Locale("en","US");

    public EnErrorMsgSupplier(){
         bundle = ResourceBundle.getBundle(source,locale);
    }

    @Override
    public String getValueByName(String name) {
        return bundle.getString(name);
    }
}
