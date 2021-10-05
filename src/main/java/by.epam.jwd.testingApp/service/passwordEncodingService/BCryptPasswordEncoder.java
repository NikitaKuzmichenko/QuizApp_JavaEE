package by.epam.jwd.testingApp.service.passwordEncodingService;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoder implements PasswordEncoder{

    public static final int SALT_ROUND = 12;
    @Override
    public String encrypt(String password) {
        if(password==null)return null;
        return BCrypt.hashpw(password, BCrypt.gensalt(SALT_ROUND));
    }

    @Override
    public boolean isMatching(String password, String encodedPassword) {
        if(password == null || encodedPassword == null) return false;
        return BCrypt.checkpw(password, encodedPassword);
    }
}
