import by.epam.jwd.testingApp.service.passwordEncoding.PasswordEncoder;
import org.junit.Assert;
import org.junit.Test;

public class PasswordEncodingTest extends Assert {
    private static final PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

    @Test
    public void matchingTrueTest(){
        String correctPassword = "1234";
        assertTrue(passwordEncoder.isMatching(correctPassword,passwordEncoder.encrypt(correctPassword)));
    }

    @Test
    public void matchingFalseTest(){
        String correctPassword = "1234";
        String wrongPassword = "1233";
        assertFalse(passwordEncoder.isMatching(wrongPassword,passwordEncoder.encrypt(correctPassword)));
    }
}
