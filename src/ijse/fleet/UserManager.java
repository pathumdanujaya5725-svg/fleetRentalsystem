package ijse.fleet;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final List<SystemUser> users = new ArrayList<>();

    public SystemUser findByUsername(String username) {
        for (SystemUser user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public SystemUser registerUser(String username, String password, String fullName) {
        if (findByUsername(username) != null) {
            return null;
        }
        SystemUser newUser = new SystemUser(username, password, fullName);
        users.add(newUser);
        return newUser;
    }

    public SystemUser login(String username, String password) {
        for (SystemUser user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean hasUsers() {
        return !users.isEmpty();
    }
}