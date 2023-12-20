package tp.minesweeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.minesweeper.model.GameField;

@Repository
public interface GameFieldRepository extends JpaRepository<GameField,Integer>
{
}
