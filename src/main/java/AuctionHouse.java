import helpers.State;
import models.LoggedUser;
import views.UserView;

import java.util.Scanner;

public class AuctionHouse {

    private State state;

    public AuctionHouse() {
        this.state = State.MAIN_MENU;
    }

    public void run() {
        UserView.printGreetings();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            UserView.printMenu();
            int usersInput = scanner.nextInt();

            switch (state) {
                case MAIN_MENU:
                    mainMenu(usersInput);
                    break;
                case SIGN_IN:

                    break;
                case SING_UP:
                    
                    break;
            }

        }
    }


    private State mainMenu(int usersInput) {
        if (LoggedUser.getInstance().isLoggedIn()) {
            switch (usersInput) {
                case 1:

                    break;
                case 2:

                    break;

                case 3:

                    break;
            }
        } else {

        }
    }

    private static void loginMenu() {

    }

}
