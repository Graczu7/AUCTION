package controllers;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInputController {
    private static Scanner scanner = new Scanner(System.in);

    public static String getTextFromUser() {
        return scanner.nextLine();
    }

    public static BigDecimal getPriceFromUser() {
        return scanner.nextBigDecimal();
    }
}
