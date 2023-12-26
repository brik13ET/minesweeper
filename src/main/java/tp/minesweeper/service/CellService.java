package tp.minesweeper.service;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.stereotype.Service;
import tp.minesweeper.model.GameCell;
import tp.minesweeper.repository.CellRepository;
import tp.minesweeper.repository.GameFieldRepository;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class CellService {
    private final CellRepository cellRepository;
    private final GameFieldRepository gameFieldRepository;

    public void addCells(GameCell[] cells)
    {
        Arrays.stream(cells)
                .forEach(gameCell -> {
                    var gameCellId = gameCell.getCellId();
                    var fieldId = gameCellId.getFieldId();
                    if (fieldId == 0)
                        throw new ParameterOutOfBoundsException("Создание ячейки для несуществующего поля", new Throwable());
                    var fieldDbo = gameFieldRepository.findById(fieldId);
                    if (fieldDbo.isEmpty())
                        throw new ParameterOutOfBoundsException("Создание ячейки для несуществующего поля", new Throwable());
                    var fieldEntity = fieldDbo.get();
                    if (
                            gameCellId.getPosX() < 0 || gameCellId.getPosX() > fieldEntity.getWidth() ||
                            gameCellId.getPosY() < 0 || gameCellId.getPosY() > fieldEntity.getHeight()
                    )
                        throw new ParameterOutOfBoundsException("Создание ячейки с координатами вне диапазона", new Throwable());
                });
        cellRepository.saveAll(Arrays.asList(cells));
    }
}
