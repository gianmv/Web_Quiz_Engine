package engine.Quiz.service;

import engine.Quiz.model.User;
import engine.Quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceJPA implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findById(String id) {
        return this.userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        if (!findById(user.getUsername()).isPresent()) {
            this.userRepository.save(user);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User already register"
            );
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }
}
