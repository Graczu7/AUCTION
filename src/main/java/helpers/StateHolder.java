package helpers;

import models.User;

public class StateHolder {
    private static StateHolder instance;
    private State state;
    private User loggedUser;

    private StateHolder() {
        this.state = State.INIT;
    }

    public static StateHolder getInstance(){
        if (instance == null){
            instance = new StateHolder();
        }
        return instance;
    }

    public User getLoggedUser(){
        return loggedUser;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
