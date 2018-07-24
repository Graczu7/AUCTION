import exceptions.*;
import exceptions.auctionExceptions.CannotModifyAuctionThatEndedException;
import exceptions.auctionExceptions.AuctionDescriptionTooShortException;
import exceptions.auctionExceptions.AuctionTitleTooShortException;
import exceptions.userExceptions.PasswordTooShortException;
import models.Auction;
import models.Category;
import models.Offer;
import models.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;

public class OfferTest {

    @Test
    public void testOfferNotNull() throws PriceNegativeValueException, AuctionDescriptionTooShortException, AuctionTitleTooShortException, PasswordTooShortException, CannotModifyAuctionThatEndedException {
        //given
        User user = new User("Jacek", "Placek","burek");
        Category category = new Category("Category");
        Auction salceson = new Auction(user, category, "Salceson","dasdfas", BigDecimal.valueOf(23.3));
        Offer offer = new Offer(user, salceson, BigDecimal.valueOf(99));

        //then
        assertNotNull(offer);
    }

    @Test
    public void testOfferAssert() throws PriceNegativeValueException, AuctionDescriptionTooShortException, AuctionTitleTooShortException, PasswordTooShortException, CannotModifyAuctionThatEndedException {
        //given
        User second = new User("Henry", "James", "Zwrotnik");
        Category category = new Category("Category");
        Auction salceson = new Auction(second, category, "Salceson","dasdfas", BigDecimal.valueOf(23.3));
        Offer offer = new Offer(second, salceson, BigDecimal.valueOf(99));

        //when
        Offer result= new Offer(second, salceson, BigDecimal.valueOf(99));

        //then
        Assert.assertEquals(offer, result);
    }

    @Test (expected = NullPointerException.class)
    public void testSetPriceIfNewPriceIsNull() throws PasswordTooShortException, AuctionDescriptionTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException, AuctionTitleTooShortException {

        //given
        User second = new User("Henry", "James", "Zwrotnik");
        Category category = new Category("Category");
        Auction salceson = new Auction(second, category, "Salceson","dasdfas", BigDecimal.valueOf(23.3));
        Offer offer = new Offer(second, salceson, BigDecimal.valueOf(0));

        offer.setPrice(null);
   }

   @Test (expected = PriceNegativeValueException.class)
    public void testSetPriceIsNegativeValue() throws PasswordTooShortException, AuctionDescriptionTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException, AuctionTitleTooShortException {

       User second = new User("Henry", "James", "Zwrotnik");
       Category category = new Category("Category");
       Auction salceson = new Auction(second, category, "Salceson","dasdfas", BigDecimal.valueOf(23.3));
       Offer offer = new Offer(second, salceson, BigDecimal.valueOf(0));

       offer.setPrice(BigDecimal.valueOf(-5));

   }

   @Test

    public void testSetPriceIsPositive() throws PasswordTooShortException, AuctionDescriptionTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException, AuctionTitleTooShortException {

       User second = new User("Henry", "James", "Zwrotnik");
       Category category = new Category("Category");
       Auction salceson = new Auction(second, category, "Salceson", "dasdfas", BigDecimal.valueOf(23.3));
       Offer offer = new Offer(second, salceson, BigDecimal.valueOf(0));

       offer.setPrice(BigDecimal.valueOf(5));

       Assert.assertEquals(BigDecimal.valueOf(5), offer.getPrice());

   }

   @Test

    public void testSetPriceSetLowerPrice() throws PasswordTooShortException, AuctionDescriptionTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException, AuctionTitleTooShortException {

       User second = new User("Henry", "James", "Zwrotnik");
       Category category = new Category("Category");
       Auction salceson = new Auction(second, category, "Salceson", "dasdfas", BigDecimal.valueOf(23.3));
       Offer offer = new Offer(second, salceson, BigDecimal.valueOf(10));

       offer.setPrice(BigDecimal.valueOf(5));


       Assert.assertEquals(BigDecimal.valueOf(5), offer.getPrice());
   }
}

