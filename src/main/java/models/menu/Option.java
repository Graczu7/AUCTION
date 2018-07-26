package models.menu;

import exceptions.InvalidInputOptionException;
import models.menu.states.OptionOne;
import models.menu.states.OptionState;
import models.menu.states.OptionThree;
import models.menu.states.OptionTwo;

public class Option {
    private static Option instance;
    private OptionState currentOptionState;

    private Option() {
    }

    public Option getInstance() {
        if (instance == null) {
            instance = new Option();
        }
        return instance;
    }

    public static void userOption(int optionNumber) throws InvalidInputOptionException {
        if (optionNumber <= 0 || optionNumber > 3) {
            throw new InvalidInputOptionException(String.valueOf(optionNumber));
        }
        switch (optionNumber) {
            case 1:
                instance.currentOptionState = new OptionOne();
                return;
            case 2:
                instance.currentOptionState = new OptionTwo();
                return;
            case 3:
                instance.currentOptionState = new OptionThree();
                return;
        }
    }

    public void execute() {
        instance.currentOptionState.execute();
    }
}
