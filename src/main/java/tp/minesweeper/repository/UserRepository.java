package tp.minesweeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.minesweeper.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByLogin(String login);
}
