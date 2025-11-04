package app.repository;

import app.model.User;
import app.model.Role;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public UserRepository() {
        users.put("ivan", new User("ivan", "1234", Role.OWNER));
        users.put("kirill", new User("kirill", "5678", Role.TENANT));
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
