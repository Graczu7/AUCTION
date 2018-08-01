import exceptions.auctionHouseExceptions.PriceValueTooLowException;
import models.Offer;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class OfferTest {
    public User user;
    public Offer offer;

    @Before
    public void setUp() throws Exception {
        user = new User("Jacek", "Placek", "burek");
        offer = new Offer(user, BigDecimal.valueOf(1));
    }

    @Test
    public void testOfferNotNull() {
        assertNotNull(offer);
    }

    @Test(expected = NullPointerException.class)
    public void testSetPriceIfNewPriceIsNull() throws Exception {
        offer.setPrice(null);
    }

    @Test(expected = PriceValueTooLowException.class)
    public void testSetPriceIsNegativeValue() throws Exception {
        offer.setPrice(BigDecimal.valueOf(-5));
    }

    @Test
    public void testSetPriceIsPositive() throws Exception {
        offer.setPrice(BigDecimal.valueOf(5));

        assertEquals(BigDecimal.valueOf(5), offer.getPrice());
    }

    @Test
    public void testSetPriceSetLowerPrice() throws Exception {
        offer.setPrice(BigDecimal.valueOf(10));

        offer.setPrice(BigDecimal.valueOf(5));

        assertEquals(BigDecimal.valueOf(5), offer.getPrice());
    }
}

