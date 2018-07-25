import helpers.MenuState;
import helpers.Menu;
import models.LoggedUser;
import views.UserView;

import java.util.Scanner;

import static controllers.UserController.*;

public class AuctionHouse {

    private MenuState menuState;
    private Menu menu;

    public AuctionHouse() {
        this.menu = Menu.getInstance();
        this.menuState = MenuState.MAIN_MENU;
    }

    public void run() {
        UserView.printGreetings();

        while (menuState != MenuState.EXIT) {
            Scanner scanner = new Scanner(System.in);
            menu.mainMenu();


        }
    }

    private MenuState userMenu(Scanner scanner) {

        int usersInput = 0;

        if (!LoggedUser.getInstance().isLoggedIn()) {
            switch (menuState) {
                case MAIN_MENU:
                    UserView.printMainMenu();
                    usersInput = getMenuOptionFromUser();
                    return mainMenu(usersInput);
                case SIGN_IN:
                    return signIn();
                case SING_UP:
                    return signUp(usersInput);
                case EXIT:
                    return MenuState.EXIT;
            }
        } else {
            switch (menuState) {
                case MAIN_MENU:
                    UserView.printMainMenu();
                    usersInput = getMenuOptionFromUser();
                    this.menuState = mainMenu(usersInput);
                    break;
                case SHOW_CATEGORIES:

                    break;
                case SHOW_AUCTIONS:

                    break;
                case SIGN_OUT:

                    break;
            }
        }
    }


    private MenuState mainMenu(int usersInput) {
        if (!LoggedUser.getInstance().isLoggedIn()) {
            switch (usersInput) {
                case 1:

                    return MenuState.SIGN_IN;
                case 2:

                    return MenuState.SING_UP;
                case 3:

                    return MenuState.EXIT;
                default:
                    return MenuState.MAIN_MENU;
            }
        } else {
            switch (usersInput) {
                case 1:

                    return MenuState.SHOW_CATEGORIES;
                case 2:
                    return MenuState.SHOW_AUCTIONS;
                case 3:
                    return MenuState.SIGN_OUT;
                default:
                    return MenuState.MAIN_MENU;
            }
        }

    }


}
