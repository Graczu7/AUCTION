import exceptions.auctionHouseExceptions.userExceptions.LoginIllegalCharacterException;
import exceptions.auctionHouseExceptions.userExceptions.NameIllegalCharacterException;
import exceptions.auctionHouseExceptions.userExceptions.PasswordIllegalCharacterException;
import exceptions.auctionHouseExceptions.userExceptions.PasswordTooShortException;
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
    public void testByConstructorIsNull() throws Exception {
        User user = new User("name", "Login", "password");
        user.setPassword("password");
        Assert.assertNotNull(user);
    }

    @Test(expected = PasswordTooShortException.class)
    public void testByShortPassword() throws Exception {
        User user = new User("Name", "Login", "password");
        user.setPassword("dupa");
    }

    @Test
    public void testByLengthPassword() throws Exception {
        User user = new User("Name", "Login", "password");
        Assert.assertNotNull(user.getPassword());
    }

    @Test
    public void testByAttributionObject() throws Exception {
        User user = new User("Name", "Login", "alternative");
        String password = "password";
        user.setPassword(password);
        Assert.assertTrue(user.getPassword().equals(password));
    }

    @Test
    public void testByCreateUsersOffer() throws Exception {
        User user = new User("Name", "Login", "password");
        Category category = new Category("Name");
        Auction auction = new Auction("Title", "Description", BigDecimal.valueOf(0));
        Offer offer = new Offer(user, BigDecimal.valueOf(0));
        ArrayList<Offer> usersOffer = new ArrayList<Offer>();
        usersOffer.add(offer);
        Assert.assertEquals(offer, usersOffer.get(0));
    }

    @Test
    public void testByCreateOwnedAuctions() throws Exception {
        User user = new User("Name", "Login", "password");
        Category category = new Category("Name");
        Auction auction = new Auction("Title", "Description", BigDecimal.valueOf(0));
        ArrayList<Auction> ownedAuctions = new ArrayList<>();
        ownedAuctions.add(auction);
        Assert.assertEquals(auction, ownedAuctions.get(0));
    }

    @Test
    public void testByCreateWoAuctions() throws Exception {
        User user = new User("Name", "Login", "password");
        Category category = new Category("Name");
        Auction auction = new Auction("Title", "Descripton", BigDecimal.valueOf(0));
        ArrayList<Auction> wonAuctions = new ArrayList<>();
        wonAuctions.add(auction);
        Assert.assertEquals(auction, wonAuctions.get(0));
    }

    @Test(expected = NameIllegalCharacterException.class)
    public void testByNameIllegalCharacter() throws Exception {
        User user = new User("Name", "Login", "password");
        user.setName("!#$@!#&!");
    }

    @Test(expected = LoginIllegalCharacterException.class)
    public void testByLoginIllegalCharacter() throws Exception {
        User user = new User("Name", "Login", "password");
        user.setLogin("!#$@!#&!");
    }

    @Test(expected = PasswordIllegalCharacterException.class)
    public void testByPasswordIllegalCharacter() throws Exception {
        User user = new User("Name", "Login", "password");
        user.setPassword("!#$@!#&!");
    }
}

