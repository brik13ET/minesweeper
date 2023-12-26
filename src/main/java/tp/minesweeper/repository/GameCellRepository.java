package tp.minesweeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.minesweeper.model.CellId;
import tp.minesweeper.model.GameCell;

public interface GameCellRepository extends JpaRepository<GameCell, CellId> {
}