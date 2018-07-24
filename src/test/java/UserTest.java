import exceptions.*;
import models.Auction;
import models.Category;
import models.Offer;
import models.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UserTest {

    @Test
    public void testByConstructorIsNull() throws PasswordTooShortException {
        User user = new User("name", "Login", null);
        user.setPassword("password");
        Assert.assertNotNull(user);
    }

    @Test(expected = PasswordTooShortException.class)
    public void testByShortPassword() throws PasswordTooShortException {
        User user = new User("Name", "Login", "password");
        user.setPassword("dupa");
    }

    @Test
    public void testByLengthPassword() throws PasswordTooShortException {
        User user = new User("Name", "Login", "password");
        Assert.assertNotNull(user.getPassword());
    }

    @Test
    public void testByAttributionObject() throws PasswordTooShortException {
        User user = new User ("Name", "Login", "alternative");
        String password = "password";
        user.setPassword(password);
        Assert.assertTrue(user.getPassword().equals(password));
    }

    @Test
    public void testByCreateUsersOffer() throws PriceNegativeValueException, PasswordTooShortException, TitleTooShortException, CannotModifyAuctionThatEndedException, DescriptionTooShortException {
        User user = new User("Name", "Login", "password");
        Category category = new Category("name");
        Auction auction = new Auction(user, category, "description", "title", BigDecimal.valueOf(10));
        Offer offer = new Offer(user, auction, BigDecimal.valueOf(11));
        ArrayList<Offer> usersOffer = new ArrayList<Offer>();
        usersOffer.add(offer);
        Assert.assertEquals(offer, usersOffer.get(0));
    }

}
