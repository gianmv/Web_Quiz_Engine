package engine.Quiz.service;

import engine.Quiz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class UserDetailsServiceJPA implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userService.findById(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(username +"not found");
        }
    }
}
