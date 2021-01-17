package engine.Quiz.service;

import engine.Quiz.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(String id);
    void save(User user);
    void delete(User user);
}
