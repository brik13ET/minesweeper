package tp.minesweeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.minesweeper.model.GameCell;
import tp.minesweeper.model.CellId;

public interface CellRepository extends JpaRepository<GameCell, CellId> {
    GameCell findOneByCellId(CellId id);
}
