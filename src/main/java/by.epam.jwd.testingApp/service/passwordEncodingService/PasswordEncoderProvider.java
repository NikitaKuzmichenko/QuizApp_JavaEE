package by.epam.jwd.testingApp.service.passwordEncodingService;

import by.epam.jwd.testingApp.service.parameterParserServise.ParserProvider;

public class PasswordEncoderProvider {

    private static PasswordEncoderProvider instance;
    private PasswordEncoder BCryptPasswordEncoder;


    private PasswordEncoderProvider(){
        BCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    public static PasswordEncoderProvider newInstance() {
        PasswordEncoderProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (PasswordEncoderProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PasswordEncoderProvider();
                }
            }
        }
        return localInstance;
    }

    public PasswordEncoder getBCryptPasswordEncoder() {
        return BCryptPasswordEncoder;
    }
}
