package controllers;

import views.UserView;

import java.util.Scanner;

public class UserInputController {
    private static Scanner scanner = new Scanner(System.in);

    public static String getInputFromUser(){
        return scanner.nextLine();
    }
}
