package tp.minesweeper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tp.minesweeper.model.GameField;
import tp.minesweeper.model.User;
import tp.minesweeper.model.UserField;
import tp.minesweeper.model.UserFieldId;
import tp.minesweeper.repository.GameFieldRepository;
import tp.minesweeper.repository.UserFieldRepository;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class FieldService {
    private final GameFieldRepository gameFieldRepository;
    private final UserFieldRepository userFieldRepository;

    public Optional<GameField> findById(Integer id) {
        return gameFieldRepository.findById(id);
    }
    Optional<UserField> findByGameFieldAndUser(GameField gfield, User usr)
    {
        return userFieldRepository.findByUidAndGFid(gfield.getId(),usr.getId());
    }
    Set<UserField> findAllByUser(User usr)
    {
        return userFieldRepository.findAllByUid(usr.getId());
    }
}
