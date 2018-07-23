import models.Auction;
import models.Offer;
import models.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;

public class OfferTest {


    @Test

    public void testOfferNotNull(){

        //given
        User user = new User("Jacek", "Placek","burek");
        Auction salceson = new Auction(user, "Salceson","dasdfas",23.3);
        Offer offer = new Offer(user, salceson, BigDecimal.valueOf(99));

        //when

        //then
        assertNotNull(offer);
    }

    @Test

    public void testOfferAssert(){
        //given
        User second = new User("Henry", "James", "Zwrotnik");
        Auction salceson = new Auction(second, "Salceson","dasdfas",23.3);
        Offer offer = new Offer(second, salceson, BigDecimal.valueOf(99));

        //when

        Offer result= new Offer(second, salceson, BigDecimal.valueOf(99));

        //then
        Assert.assertEquals(offer, result);


    }
}
