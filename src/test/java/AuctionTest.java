import exceptions.*;
import exceptions.auctionHouseExceptions.PriceValueTooLowException;
import exceptions.auctionHouseExceptions.auctionExceptions.AuctionDescriptionTooShortException;
import models.Auction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AuctionTest {

    private Auction auction;

    @Before
    public void setUp() throws Exception {
        auction = new Auction("Title","description", BigDecimal.valueOf(3.5));
    }

    @Test
    public void testAuctionNotNull(){
        assertNotNull(auction);
    }

    @Test
    public void testAuctionConstructorIsActive() {
        assertTrue(auction.isActive());
    }

    @Test
    public void testAuctionConstructorSetsDescriptionCorrectly() {
        Assert.assertEquals("description", auction.getDescription());
    }

    @Test(expected = NullPointerException.class)
    public void testAuctionConstructorForNullDescription() throws Exception {
        auction = new Auction("Title", null, BigDecimal.valueOf(3.5));
    }

    @Test(expected = AuctionDescriptionTooShortException.class)
    public void testAuctionConstructorForEmptyDescription() throws Exception {
        auction = new Auction("Title", " ", BigDecimal.valueOf(3.5));
    }

    @Test
    public void testAuctionConstructorSetsTitleCorrectly() {
        assertEquals("Title", auction.getTitle());
    }

    @Test(expected = NullPointerException.class)
    public void testAuctionConstructorForNullTitle() throws Exception {
        auction = new Auction(null, "description", BigDecimal.valueOf(3.5));
    }

    @Test
    public void testAuctionConstructorSetsPriceCorrectly() {
        assertEquals(BigDecimal.valueOf(3.5), auction.getStartingPrice());
    }

    @Test(expected = NullPointerException.class)
    public void testAuctionConstructorForPriceNull() throws Exception {
        auction = new Auction("Title", "description", null);
    }

    @Test(expected = PriceValueTooLowException.class)
    public void testAuctionConstructorForPriceNegativeValue() throws Exception {
        auction = new Auction("Title", "description", BigDecimal.valueOf(-5));
    }

    @Test
    public void testSetActiveFalse() {
        auction.disable();
        assertFalse(auction.isActive());
    }

    @Test
    public void testAuctionGetsDescription() {
        assertEquals("description", auction.getDescription());
    }

    @Test
    public void testAuctionGetsTitle() {
        assertEquals("Title", auction.getTitle());
    }

    @Test
    public void testAuctionGetsPrice() {
        assertEquals(BigDecimal.valueOf(3.5), auction.getStartingPrice());
    }
}
