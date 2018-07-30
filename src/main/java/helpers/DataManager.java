package helpers;

import models.User;

import java.io.*;
import java.util.Map;

public class DataManager {

    public void userFileReader() {

        String fileName = "userfile.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");

        }
    }

    public void userFileWriter(Map<String, User> users) {
        String fileName = "userfile.txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Map.Entry<String, User> entry : users.entrySet())
            {
                bufferedWriter.write(entry.getValue().getName());
                bufferedWriter.write(";");
                bufferedWriter.write(entry.getValue().getLogin());
                bufferedWriter.write(";");
                bufferedWriter.write(entry.getValue().getPassword());
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
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


