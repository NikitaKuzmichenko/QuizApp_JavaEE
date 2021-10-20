import by.epam.jwd.testingApp.entity.Question;
import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.service.validation.entitiesValidator.EntitiesValidatorsProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValidationTest extends Assert {
    private static final EntitiesValidatorsProvider provider = EntitiesValidatorsProvider.getInstance();

    private static User correctUser;
    private static String correctEmail = "email@gamil.com" ;
    private static String correctPassword = "password";
    private static String correctName = "name";

    @BeforeClass
    public static void init(){
        correctUser = new User();
        correctUser.setEmail(correctEmail);
        correctUser.setPassword(correctPassword);
        correctUser.setName(correctName);
    }
    @Test
    public void questionToShortValidationTest(){
        Question question = new Question();
        question.setTitle("");
       assertFalse(provider.getQuestionValidator().validateEntity(question,null,null));
    }

    @Test
    public void questionToLongValidationTest(){
        Question question = new Question();
        String longTitle = "01234567890123456789012345678901234567890123456789"+
                "012345678901234567890123456789012345678901234567890123456789";
        question.setTitle(longTitle);
        assertFalse(provider.getQuestionValidator().validateEntity(question,null,null));
    }

    @Test
    public void questionNullValidationTest(){
        assertFalse(provider.getQuestionValidator().validateEntity(null,null,null));
    }

    @Test
    public void questionValidationTest(){
        Question question = new Question();
        String title = "01234567890123456789012345678901234567890123456789";
        question.setTitle(title);
        assertTrue(provider.getQuestionValidator().validateEntity(question,null,null));
    }

    @Test
    public void userNameToShortValidationTest(){
        correctUser.setName("");
        assertFalse(provider.getUserValidator().validateEntity(correctUser,null,null));
        correctUser.setName(correctName);
    }

    @Test
    public void userNameToLongValidationTest(){
        String longName = "01234567890123456789012345678901234567890123456789"+
                "012345678901234567890123456789012345678901234567890123456789";
        correctUser.setName(longName);
        assertFalse(provider.getUserValidator().validateEntity(correctUser,null,null));
        correctUser.setName(correctName);
    }

    @Test
    public void emailToShortValidationTest(){
        correctUser.setEmail("");
        assertFalse(provider.getUserValidator().validateEntity(correctUser,null,null));
        correctUser.setEmail(correctEmail);
    }

    @Test
    public void emailToLongValidationTest(){
        String longMail = "01234567890123456789012345678901234567890123456789@gmaol.com";
        correctUser.setEmail(longMail);
        assertFalse(provider.getUserValidator().validateEntity(correctUser,null,null));
        correctUser.setEmail(correctEmail);
    }

    @Test
    public void emailWrongPatternValidationTest(){
        String longMail = "012345678";
        correctUser.setEmail(longMail);
        assertFalse(provider.getUserValidator().validateEntity(correctUser,null,null));
        correctUser.setEmail(correctEmail);
    }


    @Test
    public void passwordToShortValidationTest(){
        correctUser.setPassword("");
        assertFalse(provider.getUserValidator().validateEntity(correctUser,null,null));
        correctUser.setPassword(correctPassword);
    }

    @Test
    public void passwordToLongValidationTest(){
        String password = "0123456789012345678901234567890123456789012345678901234567890123456789";
        correctUser.setPassword(password);
        assertFalse(provider.getUserValidator().validateEntity(correctUser,null,null));
        correctUser.setPassword(correctPassword);
    }

    @Test
    public void userNullValidationTest(){
        assertFalse(provider.getUserValidator().validateEntity(null,null,null));
    }

    @Test
    public void userCorrectValidationTest(){
        assertTrue(provider.getUserValidator().validateEntity(correctUser,null,null));
    }
}
