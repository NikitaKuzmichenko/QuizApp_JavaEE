package by.epam.jwd.testingApp.service.passwordEncoding;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    public static final int SALT_ROUND = 12;

    private PasswordEncoder(){}

    private static class SingletonHolder {
        public static final PasswordEncoder HOLDER_INSTANCE = new PasswordEncoder();
    }

    public static PasswordEncoder getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public String encrypt(String password) {
        if(password==null)return null;
        return BCrypt.hashpw(password, BCrypt.gensalt(SALT_ROUND));
    }

    public boolean isMatching(String password, String encodedPassword) {
        if(password == null || encodedPassword == null) return false;
        return BCrypt.checkpw(password, encodedPassword);
    }
}
