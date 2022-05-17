package praktikum.stellarburgers;

public class UserCredentialsForAPI {

    private String email;
    private String password;

    public UserCredentialsForAPI(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
