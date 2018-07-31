package helpers;

import DataBases.AuctionsDatabase;
import DataBases.OfferDatabase;
import DataBases.UserDatabase;
import exceptions.AuctionHouseException;
import models.Auction;
import models.Offer;
import models.User;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DataManager {
    private static final String DIVIDER = ";";
    private static final String OBJECT_DIVIDER = ">";
    private static final String KEY_DIVIDER = "|";
    private static final String ENTRY_DIVIDER = "$";
    private static final String FILE_NAME = "datafile";
    private static final String USER_DB = "?USERDB";
    private static final String AUCTION_LOG_DB = "?AUCTIONLOGDB";
    private static final String AUCTION_CAT_DB = "?AUCTIONCATDB";
    private static final String AUCTION_WON_DB = "?AUCTIONWONDB";
    private static final String OFFER_DB = "?OFFERDB";


    private static FileStateHolder state = new FileStateHolder();


    public static void userFileReader() {

        String line = null;

        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(USER_DB)) {
                    state.db_type = FileStateHolder.DBType.USER_DB;
                } else if (line.contains(AUCTION_LOG_DB)) {
                    state.db_type = FileStateHolder.DBType.AUCTION_LOG_DB;
                } else if (line.contains(AUCTION_CAT_DB)) {
                    state.db_type = FileStateHolder.DBType.AUCTION_CAT_DB;
                } else if (line.contains(AUCTION_WON_DB)) {
                    state.db_type = FileStateHolder.DBType.AUCTION_WON_DB;
                } else if (line.contains(OFFER_DB)) {
                    state.db_type = FileStateHolder.DBType.OFFER_DB;
                }

                switch (state.db_type) {
                    case USER_DB: {
                        userReader(bufferedReader);
                        break;
                    }
                    case AUCTION_LOG_DB: {
                        auctionByLoginReader(bufferedReader);
                        break;
                    }
                    case AUCTION_CAT_DB: {
                        auctionByCategoryReader(bufferedReader);
                        break;
                    }
                    case AUCTION_WON_DB: {
                        auctionWonReader(bufferedReader);
                        break;
                    }
                    case OFFER_DB: {
                        offerReader(bufferedReader);
                        break;
                    }
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + FILE_NAME + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + FILE_NAME + "'");

        }
    }

    private static void userReader(BufferedReader bufferedReader) throws IOException {
        try {
            String line = null;
            if ((line = bufferedReader.readLine()) != null) {
                String[] users = line.split(OBJECT_DIVIDER);
                for (String userString : users) {
                    String[] temp = userString.split(DIVIDER);
                    UserDatabase.getInstance().addUserToDataBase(new User(temp[0], temp[1], temp[2]));
                }
            }
        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();
        }
    }

    private static void auctionByLoginReader(BufferedReader bufferedReader) throws IOException {
        try {
            String line = null;
            if ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(KEY_DIVIDER);
                String key = entries[0];
                String[] auctions = entries[1].split(OBJECT_DIVIDER);
                for (String auction : auctions) {
                    String[] auctionAsString = auction.split(DIVIDER);
                    Integer auctionID = new Integer(auctionAsString[0]);
                    String auctionTitle = auctionAsString[1];
                    String auctionDescription = auctionAsString[2];
                    BigDecimal auctionPrice = new BigDecimal(auctionAsString[3]);

                    AuctionsDatabase
                            .getInstance()
                            .addToAuctionMapByLogin(
                                    key,
                                    new Auction(auctionID,
                                            auctionTitle,
                                            auctionDescription,
                                            auctionPrice));
                }
            }
        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();
        }
    }

    private static void auctionByCategoryReader(BufferedReader bufferedReader) throws IOException {
        try {
            String line = null;
            if ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(KEY_DIVIDER);
                String key = entries[0];
                String[] auctions = entries[1].split(OBJECT_DIVIDER);
                for (String auction : auctions) {
                    String[] auctionAsString = auction.split(DIVIDER);
                    Integer auctionID = new Integer(auctionAsString[0]);
                    String auctionTitle = auctionAsString[1];
                    String auctionDescription = auctionAsString[2];
                    BigDecimal auctionPrice = new BigDecimal(auctionAsString[3]);

                    AuctionsDatabase
                            .getInstance()
                            .addToAuctionMapByCategory(
                                    key,
                                    new Auction(auctionID,
                                            auctionTitle,
                                            auctionDescription,
                                            auctionPrice));
                }
            }
        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();
        }
    }

    private static void auctionWonReader(BufferedReader bufferedReader) throws IOException {
        try {
            String line = null;
            if ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(KEY_DIVIDER);
                String key = entries[0];
                String[] auctions = entries[1].split(OBJECT_DIVIDER);
                for (String auction : auctions) {
                    String[] auctionAsString = auction.split(DIVIDER);
                    Integer auctionID = new Integer(auctionAsString[0]);
                    String auctionTitle = auctionAsString[1];
                    String auctionDescription = auctionAsString[2];
                    BigDecimal auctionPrice = new BigDecimal(auctionAsString[3]);

                    AuctionsDatabase
                            .getInstance()
                            .addAuctionWon(
                                    key,
                                    new Auction(auctionID,
                                            auctionTitle,
                                            auctionDescription,
                                            auctionPrice));
                }
            }
        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();
        }
    }

    private static void offerReader(BufferedReader bufferedReader) throws IOException {
        try {
            String line = null;
            if ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(ENTRY_DIVIDER);
                for (String entry : entries) {
                    String[] keyAndList = entry.split(KEY_DIVIDER);

                    String[] keyAuction = keyAndList[0].split(DIVIDER);
                    Integer auctionID = new Integer(keyAuction[0]);
                    String auctionTitle = keyAuction[1];
                    String auctionDescription = keyAuction[2];
                    BigDecimal auctionPrice = new BigDecimal(keyAuction[3]);

                    Auction auction = new Auction(auctionID, auctionTitle, auctionDescription, auctionPrice);

                    String[] offers = keyAndList[1].split(OBJECT_DIVIDER);
                    for (String offer : offers) {
                        String[] offerAsString = offer.split(DIVIDER);
                        String offersUserLogin = offerAsString[0];
                        BigDecimal offerPrice = new BigDecimal(offerAsString[1]);

                        Offer newOffer = new Offer(UserDatabase.getInstance().getUserByName(offersUserLogin), offerPrice);

                        OfferDatabase.getInstance().addOffersMapByAuctions(auction, newOffer);
                    }
                }
            }
        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();

        }
    }

    public static void writeAll() {
        try {

            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(USER_DB);
            bufferedWriter.write("\n");
            userWriter(UserDatabase.getInstance().getUsers(), bufferedWriter);

            bufferedWriter.write(AUCTION_LOG_DB);
            bufferedWriter.write("\n");
            auctionWriter(AuctionsDatabase.getInstance().getAuctionMapByLogin(), bufferedWriter);

            bufferedWriter.write(AUCTION_CAT_DB);
            bufferedWriter.write("\n");
            auctionWriter(AuctionsDatabase.getInstance().getAuctionMapByCategory(), bufferedWriter);

            bufferedWriter.write(AUCTION_WON_DB);
            bufferedWriter.write("\n");
            auctionWriter(AuctionsDatabase.getInstance().getAuctionsWonByUser(), bufferedWriter);

            bufferedWriter.write(OFFER_DB);
            bufferedWriter.write("\n");
            offerWriter(OfferDatabase.getInstance().getOffersMapByAuctions(), bufferedWriter);

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + FILE_NAME + "'");
        }

    }

    private static void userWriter(Map<String, User> users, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            bufferedWriter.write(entry.getValue().getName());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getValue().getLogin());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getValue().getPassword());
            bufferedWriter.write(OBJECT_DIVIDER);
        }
        bufferedWriter.write("\n");

    }

    private static void auctionWriter(Map<String, List<Auction>> auctionMap, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<String, List<Auction>> entry : auctionMap.entrySet()) {
            bufferedWriter.write(entry.getKey());
            bufferedWriter.write(KEY_DIVIDER);
            for (Auction auction : entry.getValue()) {
                bufferedWriter.write(String.valueOf(auction.getId()));
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(auction.getTitle());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(auction.getDescription());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(String.valueOf(auction.getStartingPrice()));
                bufferedWriter.write(OBJECT_DIVIDER);
            }
            bufferedWriter.write("\n");
        }
    }

    private static void offerWriter(Map<Auction, List<Offer>> offersMap, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<Auction, List<Offer>> entry : offersMap.entrySet()) {
            bufferedWriter.write(String.valueOf(entry.getKey().getId()));
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getKey().getTitle());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getKey().getDescription());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(String.valueOf(entry.getKey().getStartingPrice()));
            bufferedWriter.write(KEY_DIVIDER);
            for (Offer offer : entry.getValue()) {
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(offer.getUser().getLogin());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(String.valueOf(offer.getPrice()));
                bufferedWriter.write(OBJECT_DIVIDER);
            }
            bufferedWriter.write(ENTRY_DIVIDER);
        }
    }
}


