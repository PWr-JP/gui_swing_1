package tb.soft;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserManager {
    public static Map<String, Integer> users = new HashMap<String, Integer>();

    public UserManager() {
        users.put("test", "123".hashCode());
    }

    public boolean addUser(AddUserDto user) {
        if (isLoginTaken(user.credentials.login))
            return false;

        users.put(user.credentials.login, user.credentials.passwordHash);

        return true;
    }

    public boolean isLoginTaken(String login) {
        return users.containsKey(login);
    }

    public boolean isSignInValid(CredentialsDto credentials) {
        return Objects.equals(users.get(credentials.login), credentials.passwordHash);
    }
}
