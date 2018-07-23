import exceptions.OfferPriceNegativeException;
import exceptions.OfferTooLowException;
import models.Auction;
import models.Offer;
import models.User;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AuctionTest {

    @Test (expected = NullPointerException.class)
    public void testSetNewOfferForNullObject() throws OfferTooLowException, OfferPriceNegativeException {
        Offer newOffer = null;
        Auction auction = new Auction(new User("Name", "Login", "Password"), "Discription", "Title", BigDecimal.valueOf(3.5));
        auction.setNewOffer(newOffer);
    }

    @Test
    public void testSetNewOfferForCorrectValue() throws OfferTooLowException, OfferPriceNegativeException {
        User buyer = new User("buyer", "buyer", "password");
        User seller = new User("seller", "seller", "password");
        Auction auction = new Auction(seller,"description", "title", BigDecimal.valueOf(2));
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(3.5));

        auction.setNewOffer(newOffer);
        assertEquals(BigDecimal.valueOf(3.5), auction.getLastOffer().getPrice());
    }

    @Test (expected = OfferTooLowException.class)
    public void testSetNewOfferForOfferTooLow() throws OfferTooLowException, OfferPriceNegativeException {
        User seller = new User("seller", "seller", "password");
        Auction auction = new Auction(seller,"description", "title", BigDecimal.valueOf(2));

        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));

        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(3.5));

        auction.setNewOffer(oldOffer);
        auction.setNewOffer(newOffer);
    }
}
