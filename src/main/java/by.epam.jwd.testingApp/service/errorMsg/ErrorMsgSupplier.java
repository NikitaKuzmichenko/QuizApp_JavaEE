package by.epam.jwd.testingApp.service.errorMsg;

public interface ErrorMsgSupplier {
    String source = "errors";

    String getValueByName(String name);
}
