package tp.minesweeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.minesweeper.model.Difficulty;

public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {
}
