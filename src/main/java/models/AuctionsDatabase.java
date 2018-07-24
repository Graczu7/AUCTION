package models;

import exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionsDatabase {
    private static AuctionsDatabase instance;
    private Map<Integer, Auction> activeAuctions;
    private Map<Integer, Auction> archivedAuctions;

    private AuctionsDatabase() {
        this.activeAuctions = new HashMap<>();
        this.archivedAuctions = new HashMap<>();
    }

    public static AuctionsDatabase getInstance(){
        if (instance == null){
            instance = new AuctionsDatabase();
        }
        return instance;
    }

    public void addAuctionToDatabase(Auction auctionToAdd) throws SuchAuctionAlreadyExistsException, CannotAddAuctionThatIsNotActiveException {
        if (!auctionToAdd.isActive()){
            throw new CannotAddAuctionThatIsNotActiveException();
        }
        if (this.activeAuctions.containsKey(auctionToAdd.getId())){
            throw new SuchAuctionAlreadyExistsException();
        }
        this.activeAuctions.put(auctionToAdd.getId(), auctionToAdd);
    }

    public void archiveAuction(Auction auction) throws AuctionCanBeExistsInOnlyOneDatabaseException, AuctionAlreadyArchivedException {
        if (activeAuctions.containsKey(auction.getId()) && !archivedAuctions.containsKey(auction.getId())){
            archivedAuctions.put(auction.getId(), auction);
            activeAuctions.remove(auction.getId());
        } else if (activeAuctions.containsKey(auction.getId()) && archivedAuctions.containsKey(auction.getId())){
            throw new AuctionCanBeExistsInOnlyOneDatabaseException();
        } else if (!activeAuctions.containsKey(auction.getId()) && archivedAuctions.containsKey(auction.getId())){
            throw new AuctionAlreadyArchivedException();
        } else {
            throw new UnknownError();
        }
    }

    public List<Auction> getActiveAuctionsAsList(){
        List<Auction> auctions = new ArrayList<>();
        for (Map.Entry<Integer, Auction> entry : activeAuctions.entrySet()) {
            auctions.add(entry.getValue());
        }
        return auctions;
    }

    public List<Auction> getArchivedAuctionsAsList(){
        List<Auction> auctions = new ArrayList<>();
        for (Map.Entry<Integer, Auction> entry : archivedAuctions.entrySet()) {
            auctions.add(entry.getValue());
        }
        return auctions;
    }

    public Auction getAuctionById(Integer id) throws NoSuchAuctionInDatabaseException {
        if (activeAuctions.containsKey(id)){
            return activeAuctions.get(id);
        } else if (archivedAuctions.containsKey(id)){
            return archivedAuctions.get(id);
        } else {
            throw new NoSuchAuctionInDatabaseException();
        }
    }
}
