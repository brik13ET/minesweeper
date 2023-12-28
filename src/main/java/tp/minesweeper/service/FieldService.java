package tp.minesweeper.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.minesweeper.dto.GameCellDto;
import tp.minesweeper.dto.GameFieldDto;
import tp.minesweeper.dto.UserCellDto;
import tp.minesweeper.mapper.Mapper;
import tp.minesweeper.model.*;
import tp.minesweeper.repository.GameCellRepository;
import tp.minesweeper.repository.GameFieldRepository;
import tp.minesweeper.repository.UserFieldRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FieldService {
    private final GameFieldRepository gameFieldRepository;
    private final UserFieldRepository userFieldRepository;
    private final GameCellRepository gameCellRepository;


    @Autowired
    private final Mapper<GameField,GameFieldDto> gameFieldGameFieldDtoMapper;
    @Autowired
    private final Mapper<GameCell, GameCellDto> gameCellGameCellDtoMapper;
    @Autowired
    private final Mapper<UserCell, UserCellDto> userCellUserCellDtoMapper;

    public Optional<GameFieldDto> findGameFieldById(Integer id) {
        return gameFieldRepository.findById(id)
                .map(
                        gameField ->
                                gameFieldGameFieldDtoMapper.map(
                                        gameField
                                )
                );
    }
//    Optional<UserField> findByGameFieldAndUser(GameField gfield, User usr)
//    {
//        return userFieldRepository.findByUidAndGFid(gfield.getId(),usr.getId());
//    }
    public Set<UserField> findAllByUser(User usr)
    {
        return userFieldRepository.findAllByUid(usr.getId());
    }

    public List<GameFieldDto> findAllGames()
    {
        return gameFieldRepository
                .findAll()
                .stream()
                .map( gf -> gameFieldGameFieldDtoMapper.map(gf) )
                .toList();
    }

    public GameField newGameField(int width, int height, int mines)
    {
        var ret = gameFieldRepository.save(new GameField(width, height, mines));
        return ret;
    }

    public GameFieldDto newGameFieldCells(int width, int height, Boolean[] cells)
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
        return gameFieldGameFieldDtoMapper.map(gf);
    }
}
