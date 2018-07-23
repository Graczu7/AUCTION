import exceptions.*;
import models.Auction;
import models.Offer;
import models.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;

public class OfferTest {

    @Test
    public void testOfferNotNull() throws PriceNegativeValueException, DescriptionTooShortException, TitleTooShortException {
        //given
        User user = new User("Jacek", "Placek","burek");
        Auction salceson = new Auction(user, "Salceson","dasdfas", BigDecimal.valueOf(23.3));
        Offer offer = new Offer(user, salceson, BigDecimal.valueOf(99));

        //then
        assertNotNull(offer);
    }

    @Test
    public void testOfferAssert() throws PriceNegativeValueException, DescriptionTooShortException, TitleTooShortException {
        //given
        User second = new User("Henry", "James", "Zwrotnik");
        Auction salceson = new Auction(second, "Salceson","dasdfas", BigDecimal.valueOf(23.3));
        Offer offer = new Offer(second, salceson, BigDecimal.valueOf(99));

        //when
        Offer result= new Offer(second, salceson, BigDecimal.valueOf(99));

        //then
        Assert.assertEquals(offer, result);
    }
}
