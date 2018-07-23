import exceptions.NewOffersUserEqualsLastOffersUserException;
import exceptions.OfferPriceNegativeValueException;
import exceptions.OfferTooLowException;
import models.Auction;
import models.Offer;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AuctionTest {
    private User seller;
    private Auction auction;

    @Before
    public void setup(){
        seller = new User("Name", "Login", "Password");
        auction = new Auction(seller, "Description", "Title", BigDecimal.valueOf(3.5));
    }

    @Test
    public void testAuctionGetsOwner(){
        assertEquals(seller, auction.getOwner());
    }

    @Test
    public void testAuctionGetsDescription(){
        assertEquals("Description", auction.getDescription());
    }

    @Test
    public void testAuctionGetsTitle(){
        assertEquals("Title", auction.getTitle());
    }

    @Test
    public void testAuctionGetsPrice(){
        assertEquals(BigDecimal.valueOf(3.5), auction.getPrice());
    }

    @Test
    public void testAuctionOffersListNotNull(){
        assertNotNull(auction.getOffersList());
    }

    @Test
    public void testAuctionOffersListEmpty(){
        assertTrue(auction.getOffersList().isEmpty());
    }

    @Test (expected = NullPointerException.class)
    public void testSetNewOfferForNullObject() throws OfferTooLowException, NewOffersUserEqualsLastOffersUserException {
        Offer newOffer = null;
        auction.setNewOffer(newOffer);
    }

    @Test
    public void testSetNewOfferForOfferHigherThanPrice() throws OfferTooLowException, OfferPriceNegativeValueException, NewOffersUserEqualsLastOffersUserException {
        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, new BigDecimal(5));

        auction.setNewOffer(newOffer);
        assertEquals(newOffer, auction.getLastOffer());
    }

    @Test
    public void testSetNewOfferForOfferHigherThanLastOffer() throws OfferTooLowException, OfferPriceNegativeValueException, NewOffersUserEqualsLastOffersUserException {
        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));

        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(7.5));

        auction.setNewOffer(oldOffer);
        auction.setNewOffer(newOffer);
        assertEquals(newOffer, auction.getLastOffer());
    }

    @Test (expected = OfferTooLowException.class)
    public void testSetNewOfferForOfferEqualsLastOffer() throws OfferTooLowException, OfferPriceNegativeValueException, NewOffersUserEqualsLastOffersUserException {
        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));

        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(5));

        auction.setNewOffer(oldOffer);
        auction.setNewOffer(newOffer);
    }

    @Test (expected = OfferTooLowException.class)
    public void testSetNewOfferForOfferLowerThanLastOffer() throws OfferTooLowException, OfferPriceNegativeValueException, NewOffersUserEqualsLastOffersUserException {
        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));

        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(3.5));

        auction.setNewOffer(oldOffer);
        auction.setNewOffer(newOffer);
    }

    @Test (expected = OfferTooLowException.class)
    public void testSetNewOfferForOfferEqualsPrice() throws OfferTooLowException, OfferPriceNegativeValueException, NewOffersUserEqualsLastOffersUserException {
        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(3.5));

        auction.setNewOffer(newOffer);
    }

    @Test (expected = OfferTooLowException.class)
    public void testSetNewOfferForOfferLowerThanPrice() throws OfferTooLowException, OfferPriceNegativeValueException, NewOffersUserEqualsLastOffersUserException {
        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(1.5));

        auction.setNewOffer(newOffer);
    }



}
