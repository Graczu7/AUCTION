import java.util.Scanner;

public class Users {

    private String name;
    private String login;
    private String password;

    public Users(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword (String newPassword) throws PasswordToShortException {

        if(newPassword.length() < 5){
            throw new PasswordToShortException();
        }
        this.password = newPassword;
    }
}

