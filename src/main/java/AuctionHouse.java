import exceptions.userExceptions.UserNotLoggedInException;
import helpers.State;
import models.LoggedUser;
import views.UserView;

import java.util.Scanner;

public class AuctionHouse {

    private State state;

    public AuctionHouse() {
        this.state = State.MAIN_MENU;
    }

    public static void run() {
        UserView.printGreetings();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            UserView.printMenu();
            int usersInput = scanner.nextInt();

            mainMenu(usersInput);

        }
    }


    private static void mainMenu(int usersInput) {
        try {
            LoggedUser.getInstance().getUser();
        } catch (UserNotLoggedInException e) {
            switch (usersInput) {
                case 1:
                    loginMenu();
                    break;
                case 2:

                    break;

                case 3:

                    break;
            }
        }
    }

    private static void loginMenu(){

    }

}
