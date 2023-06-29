package org.eclipse.jakarta.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthService {

    private Map<String, String> users = new HashMap<>();
    private Map<String, String> activeTokens = new HashMap<>();

    public String login(String username, String password) {
        String storedPassword = users.get(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            String token = UUID.randomUUID().toString();
            activeTokens.put(token, username);
            return token;
        }
        return null;
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }

        users.put(username, password);
        return true;
    }

    public void logout(String token) {
        activeTokens.remove(token);
    }

    public boolean validateToken(String token) {
        return activeTokens.containsKey(token);
    }
}
