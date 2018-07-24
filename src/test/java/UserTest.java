import exceptions.userExceptions.PasswordTooShortException;
import models.User;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test(expected = PasswordTooShortException.class)
    public void testByShortPassword() throws PasswordTooShortException {
        User user = new User("Name", "Login", null);
        user.setPassword("dupa");
    }

    @Test
    public void testByLengthPassword() throws PasswordTooShortException {
        User user = new User("Name", "Login", null);
        user.setPassword("password");
        Assert.assertNotNull(user.getPassword());
    }

    @Test
    public void testByAttributionObject() throws PasswordTooShortException {
        User user = new User ("Name", "Login", null);
        String password = "password";
        user.setPassword(password);
        Assert.assertTrue(user.getPassword().equals(password));
    }
}
