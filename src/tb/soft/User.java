package tb.soft;

public class User {
    private String username;
    private char[] password;

    public User(String username, char[] password) {
        this.username = username;
        this.password = password.clone();
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(char[] password) {
        this.password = password;
    }
    public char[] getPassword() {
        return password;
    }
}
