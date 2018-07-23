import exceptions.OfferPriceNegativeException;
import exceptions.OfferTooLowException;
import models.Auction;
import models.Offer;
import models.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuctionTest {

    @Test (expected = NullPointerException.class)
    public void testSetNewOfferForNullObject() throws OfferTooLowException, OfferPriceNegativeException {
        Offer newOffer = null;
        Auction auction = new Auction(new User("Name", "Login", "Password"), "Discription", "Title", 3.5);
        auction.setNewOffer(newOffer);
    }

    @Test
    public void testSetNewOfferForCorrectValue() throws OfferTooLowException, OfferPriceNegativeException {
        Offer newOffer = new Offer("name", "title", "description", 3.5);

        Auction auction = new Auction(new User("Name", "Login", "Password"), "Discription", "Title", 3.5);
        auction.setNewOffer(newOffer);
        double expected = 3.5;
        assertEquals(expected, auction.getLastOffer().getPrice(), 0.001);
    }

    @Test (expected = OfferTooLowException.class)
    public void testSetNewOfferForOfferTooLow() throws OfferTooLowException, OfferPriceNegativeException {
        Offer newOffer = new Offer();
        newOffer.setPrice(3.5);

        Auction auction = new Auction(new User("Name", "Login", "Password"), "Discription", "Title", 3.5);
        Offer oldOffer = new Offer();
        oldOffer.setPrice(2.5);
        auction.setNewOffer(oldOffer);
        auction.setNewOffer(newOffer);
    }
}
