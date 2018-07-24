package views;

import models.User;

public class UserView {
    public static void printUserLoginConfirmation(User user) {
        System.out.println("Zalogowano: " + user);
    }
}
