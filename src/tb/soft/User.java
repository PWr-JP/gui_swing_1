package tb.soft;

public class User {
    private String username;
    private char[] password;

    public User(String username, char[] password) {
        this.username = username;
        this.password = password.clone();
    }

    public String getUsername() {
        return username;
    }
    public char[] getPassword() {
        return password;
    }
}
