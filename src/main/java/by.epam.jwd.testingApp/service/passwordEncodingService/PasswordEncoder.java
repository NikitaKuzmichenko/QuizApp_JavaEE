package by.epam.jwd.testingApp.service.passwordEncodingService;

public interface PasswordEncoder {

    String encrypt(String password);
    boolean isMatching(String password,String encodedPassword);
}
