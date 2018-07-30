package helpers;

import DataBases.AuctionsDatabase;
import DataBases.UserDatabase;
import exceptions.userExceptions.LoginAlreadyExistsException;
import exceptions.userExceptions.PasswordTooShortException;
import models.Auction;
import models.User;

import java.io.*;
import java.util.List;
import java.util.Map;

public class DataManager {
    private static final String DIVIDER = ";";
    private static final String OBJECT_DIVIDER = "/";
    private static final String FILE_NAME = "datafile.data";


    public void userFileReader() throws LoginAlreadyExistsException, PasswordTooShortException {

        String fileName = "userfile.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(DIVIDER);
                UserDatabase.getInstance().addUserToDataBase(new User(strings[0], strings[1], strings[2]));
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");

        }
    }

    public void writeAll() {
        try {

            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("{UserDatabase}\n");
            userFileWriter(UserDatabase.getInstance().getUsers(), bufferedWriter);

            bufferedWriter.write("{AuctionDatabase.auctionMapByLogin}\n");
            auctionFileWriter(AuctionsDatabase.getInstance().getAuctionMapByLogin());

            bufferedWriter.write("{AuctionDatabase.auctionMapByCategory}\n");
            auctionFileWriter(AuctionsDatabase.getInstance().getAuctionMapByCategory());

            bufferedWriter.write("{AuctionDatabase.auctionsWonByUser}\n");
            auctionFileWriter(AuctionsDatabase.getInstance().getAuctionsWonByUser());



            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + FILE_NAME + "'");
        }

    }

    private void userFileWriter(Map<String, User> users, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            bufferedWriter.write(entry.getValue().getName());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getValue().getLogin());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getValue().getPassword());
            bufferedWriter.write("\n");
        }

    }

    private void auctionFileWriter(Map<String, List<Auction>> auctionMap, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<String, List<Auction>> entry : auctionMap.entrySet()) {
            bufferedWriter.write(entry.getKey());
            bufferedWriter.write(OBJECT_DIVIDER);
            for (Auction auction : entry.getValue()) {
                bufferedWriter.write(auction.getId());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(auction.getTitle());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(auction.getDescription());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(String.valueOf(auction.getStartingPrice()));
                bufferedWriter.write("\n");
            }
        }
    }

    

    public void auctionFileWriter(Map<String, List<Auction>> auctionsMap) {

    }

//    public static void main(String[] args) throws PasswordTooShortException {
//
//        List<User> users = new ArrayList<>();
//
//        users.add(new User("jan", "kowalski", "123456"));
//        users.add(new User("maria", "kowalska", "1aa23456"));
//        users.add(new User("ula", "nowak", "1234vv56"));
//
//
//        DataManager dataManager = new DataManager();
//        dataManager.userFileWriter(users);
//    }
}


