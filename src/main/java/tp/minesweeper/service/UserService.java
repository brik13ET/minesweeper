package tp.minesweeper.service;

import jakarta.transaction.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tp.minesweeper.model.User;
import tp.minesweeper.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
    public Optional<User> findById(Integer id)
    {
        return userRepository.findById(id);
    }

    public Optional<User> findByLogin(String login)
    {
        return userRepository.findByLogin(login);
    }

    public void newUser(String login, String pass)
    {
        userRepository.save(new User(login, pass));
    }
}
