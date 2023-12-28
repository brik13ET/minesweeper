package tp.minesweeper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tp.minesweeper.model.*;
import tp.minesweeper.repository.GameCellRepository;
import tp.minesweeper.repository.GameFieldRepository;
import tp.minesweeper.repository.UserFieldRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FieldService {
    private final GameFieldRepository gameFieldRepository;
    private final UserFieldRepository userFieldRepository;
    private final GameCellRepository gameCellRepository;

    public Optional<GameField> findGameFieldById(Integer id) {
        return gameFieldRepository.findById(id);
    }
    Optional<UserField> findByGameFieldAndUser(GameField gfield, User usr)
    {
        return userFieldRepository.findByUidAndGFid(gfield.getId(),usr.getId());
    }
    public Set<UserField> findAllByUser(User usr)
    {
        return userFieldRepository.findAllByUid(usr.getId());
    }

    public GameField newGameField(int width, int height, int mines)
    {
        var ret = gameFieldRepository.save(new GameField(width, height, mines));
        return ret;
    }

    public GameField newGameFieldCells(int width, int height, Boolean[] cells)
    {
        int pCount = (int)Arrays.stream(cells).filter( aBoolean -> aBoolean ).count();
        var gf = gameFieldRepository.save(new GameField(width, height, pCount));
        for (int i = 0; i < cells.length; i++)
        {
            var gameCell = cells[i];
            var elem = GameCell.builder()
                .planted(gameCell)
                .cellId(
                    CellId.builder()
                        .fieldId(gf.getId())
                        .posX( i % width)
                        .posY( i / width)
                        .build()
                )
                .build();
            gameCellRepository.save(elem);
        }
        return gf;
    }
}
